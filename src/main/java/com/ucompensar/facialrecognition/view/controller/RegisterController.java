package com.ucompensar.facialrecognition.view.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.math.NumberUtils;
import org.ocpsoft.rewrite.faces.navigate.Navigate;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CaptureEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ucompensar.facialrecognition.business.dto.collegecareer.CollegeCareerDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.DetectDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.FaceDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.ResultDto;
import com.ucompensar.facialrecognition.business.dto.faceuser.FaceUserDto;
import com.ucompensar.facialrecognition.business.dto.faceuser.PersonalInformationDto;
import com.ucompensar.facialrecognition.business.dto.faculty.FacultyDto;
import com.ucompensar.facialrecognition.business.service.CollegeCareerService;
import com.ucompensar.facialrecognition.business.service.FacePlusService;
import com.ucompensar.facialrecognition.business.service.FaceUserService;
import com.ucompensar.facialrecognition.business.service.FacultyService;
import com.ucompensar.facialrecognition.util.enums.FacesDetectEnum;
import com.ucompensar.facialrecognition.util.enums.GenderEnum;
import com.ucompensar.facialrecognition.util.enums.IdTypeEnum;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ViewScoped
@Named
public class RegisterController {

   @Autowired
   private FacePlusService facePlusService;

   @Autowired
   private FaceUserService faceUserService;

   @Autowired
   private CollegeCareerService collegeCareerService;

   @Autowired
   private FacultyService facultyService;

   @Getter
   @Setter
   private FaceUserDto faceUserDto;

   @Getter
   @Setter
   private List<CollegeCareerDto> collegeCareerDtos;

   @Getter
   @Setter
   private List<FacultyDto> facultyDtos;

   @Getter
   @Setter
   private List<GenderEnum> genderEnums;

   @Getter
   @Setter
   private List<IdTypeEnum> idTypeEnums;

   @Getter
   @Setter
   private boolean attached;

   @Value("${base.path.name}")
   private String basePathName;

   @Value("${face.plus.probability}")
   private Integer probability;

   @Value("${face.plus.detected.glass}")
   private String detectedGlass;

   @Getter
   @Setter
   private int retry;

   private String imageBase65;

   private FaceDto faceDto;

   @Value("${face.plus.facesets.token}")
   private String faceSetsToken;

   @PostConstruct
   public void init() {
      attached = false;
      imageBase65 = null;
      faceDto = null;
      faceUserDto = FaceUserDto.builder().build();
      faceUserDto.setPersonalInformation(PersonalInformationDto.builder().build());
      facultyDtos = facultyService.findAll();
      idTypeEnums = Arrays.asList(IdTypeEnum.values());
      genderEnums = Arrays.asList(GenderEnum.values());
   }

   public void onCollageCareerChange() {
      if (faceUserDto.getPersonalInformation().getFaculty() != null) {
         collegeCareerDtos = collegeCareerService.findByFaculty(this.faceUserDto.getPersonalInformation().getFaculty().getId());
      } else {
         collegeCareerDtos = new ArrayList<>();
      }
   }

   public void clean() {
      faceUserDto = new FaceUserDto();
      collegeCareerDtos = new ArrayList<>();
   }

   public void sleep() throws InterruptedException {
      attached = !attached;
      TimeUnit.SECONDS.sleep(2);
   }

   public void sleepImageClear() throws InterruptedException {
      attached = false;
      imageBase65 = null;
      TimeUnit.SECONDS.sleep(2);
   }

   public void sleepUserCapture() throws InterruptedException {
      final PrimeFaces current = PrimeFaces.current();
      current.executeScript("photCamCapture()");
      TimeUnit.SECONDS.sleep(3);
   }

   public String save() {
      faceUserService.save(this.faceUserDto, this.faceDto, this.imageBase65, this.faceSetsToken);
      return Navigate.to(this.basePathName.concat("/login")).build();
   }

   public void onCapture(CaptureEvent captureEvent) {
      final String imageBase64 = Base64.getEncoder().encodeToString(captureEvent.getData());
      final DetectDto detect = facePlusService.detect(imageBase64);
      detectFace(detect, imageBase64);
   }

   private void detectFace(final DetectDto detectDto, final String imgBase64) {
      FacesDetectEnum facesDetectEnum = null;
      if (detectDto.getFaceNum() > NumberUtils.INTEGER_ONE) {
         facesDetectEnum = FacesDetectEnum.FACE_DETECT_TWO;
      }
      if (detectDto.getFaceNum().equals(NumberUtils.INTEGER_ZERO)) {
         facesDetectEnum = FacesDetectEnum.FACE_DETECT_THREE;
      }

      final FaceDto faceDto = detectDto.getFaces().get(NumberUtils.INTEGER_ZERO);
      if (!faceDto.getAttributes().getGlass().getValue().equals(this.detectedGlass)) {
         if (faceDto.getAttributes().getEyestatusDto().getRightEyeStatus().getNormalGlassEyeOpen() < this.probability
               && faceDto.getAttributes().getEyestatusDto().getLeftEyeStatus().getNormalGlassEyeOpen() < this.probability) {
            facesDetectEnum = FacesDetectEnum.FACE_DETECT_FOUR;
         }
      } else {
         if (faceDto.getAttributes().getEyestatusDto().getRightEyeStatus().getNoGlassEyeOpen() < this.probability
               && faceDto.getAttributes().getEyestatusDto().getLeftEyeStatus().getNoGlassEyeOpen() < this.probability) {
            facesDetectEnum = FacesDetectEnum.FACE_DETECT_FIVE;
         }
      }
      searchFace(facesDetectEnum == null ? FacesDetectEnum.FACE_DETECT_ONE : facesDetectEnum, faceDto, imgBase64);
   }

   private void searchFace(final FacesDetectEnum detect, final FaceDto faceDto, final String imgBase64) {
      if (detect.getFaceNum() == NumberUtils.INTEGER_ONE) {
         final ResultDto resultDto = facePlusService.search(imgBase64, this.faceSetsToken);
         if (resultDto != null && resultDto.getConfidence() >= this.probability) {
            FacesContext
                  .getCurrentInstance()
                  .addMessage("messagesUser", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ya existe en el sistema"));
         } else {
            final PrimeFaces current = PrimeFaces.current();
            this.faceUserDto.setFaceToken(faceDto.getFaceToken());
            this.faceDto = faceDto;
            this.imageBase65 = imgBase64;
            attached = !attached;
            current.executeScript("succesRegister()");
            current.ajax().update("photoCamUser");
            current.ajax().update("previewPhotoCam");
            FacesContext
                  .getCurrentInstance()
                  .addMessage("messagesUser", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha registrado la imagen correctamente"));
         }
      } else {
         FacesContext.getCurrentInstance().addMessage("messagesUser", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detect.getMessage()));
      }
   }

}
