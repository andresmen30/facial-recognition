package com.ucompensar.facialrecognition.view.controller;

import java.io.Serializable;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.math.NumberUtils;
import org.ocpsoft.rewrite.faces.navigate.Navigate;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CaptureEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.annotation.ApplicationScope;

import com.ucompensar.facialrecognition.business.dto.faceplus.AttributesDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.DetectDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.FaceDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.ResultDto;
import com.ucompensar.facialrecognition.business.service.FacePlusService;
import com.ucompensar.facialrecognition.business.service.FaceUserService;
import com.ucompensar.facialrecognition.business.service.MailService;
import com.ucompensar.facialrecognition.util.enums.FacesDetectEnum;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ApplicationScope
public class LoginController implements Serializable {

   @Value("${face.plus.probability}")
   private Integer probability;

   @Autowired
   private FaceUserService faceUserService;

   @Autowired
   private FacePlusService facePlusService;

   @Getter
   @Setter
   private boolean attached;

   @Getter
   @Setter
   private int retry;

   @Value("${base.path.name}")
   private String basePathName;

   @Value("${face.plus.detected.glass}")
   private String detectedGlass;

   @Value("${face.plus.facesets.token}")
   private String faceSetsToken;

   @Value("${face.plus.facesets.intruder.token}")
   private String faceSetsTokenIntruder;

   @Autowired
   private MailService mailService;

   private AttributesDto attributesDto;

   @PostConstruct()
   public void init() {
      onload();
   }

   public void onload() {
      attached = false;
      attributesDto = null;
      retry = NumberUtils.INTEGER_ZERO;
      PrimeFaces.current().executeScript("closeCam()");
   }

   private static final int INTEGER_NUMBER_THREE = 3;

   public void onCapture(CaptureEvent captureEvent) {
      final String imageBase64 = Base64.getEncoder().encodeToString(captureEvent.getData());

      final DetectDto detect = facePlusService.detect(imageBase64);
      detectFace(detect, imageBase64);
      this.attributesDto = null;
   }

   private void detectFace(final DetectDto detectDto, final String imgBase64) {
      FacesDetectEnum facesDetectEnum = null;
      if (detectDto.getFaceNum() > NumberUtils.INTEGER_ONE) {
         facesDetectEnum = FacesDetectEnum.FACE_DETECT_TWO;
      }
      if (detectDto.getFaceNum().equals(NumberUtils.INTEGER_ZERO)) {
         facesDetectEnum = FacesDetectEnum.FACE_DETECT_THREE;
      }
      if (facesDetectEnum == null) {
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
         this.attributesDto = faceDto.getAttributes();
         searchFace(facesDetectEnum == null ? FacesDetectEnum.FACE_DETECT_ONE : facesDetectEnum, imgBase64);
      } else {
         messageErrorEnum(facesDetectEnum);
      }
   }

   private void searchFace(final FacesDetectEnum detect, final String imgBase64) {
      final PrimeFaces current = PrimeFaces.current();
      if (detect.getFaceNum() == NumberUtils.INTEGER_ONE) {
         final ResultDto searchIntruder = facePlusService.search(imgBase64, this.faceSetsTokenIntruder);
         if (searchIntruder == null || searchIntruder.getConfidence() <= this.probability) {
            final ResultDto resultDto = facePlusService.search(imgBase64, this.faceSetsToken);
            if (resultDto.getConfidence() >= this.probability) {
               retry = NumberUtils.INTEGER_ZERO;
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Su ingreso es exitoso"));
               current.executeScript("errorFaceId()");
               faceUserService.saveLog(resultDto.getFaceToken(), this.attributesDto, imgBase64);
            } else {
               retry++;
               if (retry == INTEGER_NUMBER_THREE) {
                  attached = false;
                  current.executeScript("closeCam()");
                  current.ajax().update("photoCam");
                  current.ajax().update("previewPhotoCam");
                  FacesContext
                        .getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Ha superado el número de intentos para ingresar"));
                  mailService.sendMailHtml(imgBase64);
                  faceUserService.saveLogIntruder(resultDto.getFaceToken(), this.attributesDto, imgBase64, this.faceSetsTokenIntruder);
                  retry = NumberUtils.INTEGER_ZERO;
               } else {
                  FacesContext
                        .getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                              "No se ha encontrado en" + " nuestro sistema, reintento " + retry));
                  current.executeScript("errorFaceId()");
               }
            }
         } else {
            attached = false;
            current.executeScript("closeCam()");
            current.ajax().update("photoCam");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "El acceso ha sido denegado"));
            faceUserService.saveLogIntruder(searchIntruder.getFaceToken(), this.attributesDto, imgBase64, this.faceSetsTokenIntruder);
         }
      } else {
         messageErrorEnum(detect);
      }
   }

   private void messageErrorEnum(final FacesDetectEnum detectEnum) {
      final PrimeFaces current = PrimeFaces.current();
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", detectEnum.getMessage()));
      current.executeScript("errorFaceId()");
   }

   public void sleep() throws InterruptedException {
      attached = !attached;
      TimeUnit.SECONDS.sleep(2);
   }

   public String redirect(final String navigate) {
      return Navigate.to(this.basePathName.concat(navigate)).build();
   }

}
