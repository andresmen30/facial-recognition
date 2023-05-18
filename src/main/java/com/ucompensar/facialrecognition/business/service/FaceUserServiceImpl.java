package com.ucompensar.facialrecognition.business.service;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ucompensar.facialrecognition.business.dto.faceplus.AttributesDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.FaceDto;
import com.ucompensar.facialrecognition.business.dto.faceuser.AttributesFaceDto;
import com.ucompensar.facialrecognition.business.dto.faceuser.FaceUserDto;
import com.ucompensar.facialrecognition.business.mapper.AttributesFaceMapper;
import com.ucompensar.facialrecognition.business.mapper.PersonalInformationMapper;
import com.ucompensar.facialrecognition.infrastructure.entity.FaceUser;
import com.ucompensar.facialrecognition.infrastructure.entity.PersonalInformation;
import com.ucompensar.facialrecognition.infrastructure.repository.AttributeFaceRepository;
import com.ucompensar.facialrecognition.infrastructure.repository.FaceUserRepository;
import com.ucompensar.facialrecognition.infrastructure.repository.PersonalInformationRepository;
import com.ucompensar.facialrecognition.util.enums.DecisionEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaceUserServiceImpl implements FaceUserService {

   private final FacePlusService facePlusService;

   private final FaceUserRepository userRepository;

   private final PersonalInformationRepository personalInformationRepository;

   private final AttributeFaceRepository attributeFaceRepository;

   private final AttributesFaceMapper attributesFaceMapper;

   private final PersonalInformationMapper personalInformationMapper;

   @Override
   public void save(final FaceUserDto faceUserDto, final FaceDto faceDto, final String imageBase64, final String faceSetsToken) {
      faceUserDto.setFaceToken(faceDto.getFaceToken());
      final FaceUser faceUser = userRepository.save(FaceUser.builder().faceToken(faceDto.getFaceToken()).build());
      saveAttributesFace(faceUser.getId(), faceDto.getAttributes(), DecisionEnum.NO, imageBase64);
      final PersonalInformation personalInformation = personalInformationMapper.toEntity(faceUserDto.getPersonalInformation());
      this.personalInformationRepository.save(personalInformation);
      faceUser.setPersonalInformation(PersonalInformation.builder().id(personalInformation.getId()).build());
      this.userRepository.save(faceUser);
      facePlusService.addFace(faceDto.getFaceToken(), faceSetsToken);
   }

   @Override
   @Async
   public void saveLog(final String faceToken, final AttributesDto attributesDto, final String imageBase64) {
      final FaceUser faceUser = userRepository.findByFaceToken(faceToken);
      saveAttributesFace(faceUser.getId(), attributesDto, DecisionEnum.NO, imageBase64);
   }

   @Override
   @Async
   public void saveLogIntruder(final String faceToken, final AttributesDto attributesDto, final String imageBase64, final String faceSetsToken) {
      saveAttributesFace(null, attributesDto, DecisionEnum.YES, imageBase64);
      facePlusService.addFace(faceToken, faceSetsToken);
   }

   private void saveAttributesFace(final String faceId, final AttributesDto attribute, final DecisionEnum intruder, final String image) {
      final AttributesFaceDto attributesFaceUserDto = AttributesFaceDto
            .builder()
            .anger(attribute.getEmotion().getAnger())
            .disgust(attribute.getEmotion().getDisgust())
            .fear(attribute.getEmotion().getFear())
            .neutral(attribute.getEmotion().getNeutral())
            .sadness(attribute.getEmotion().getSadness())
            .surprise(attribute.getEmotion().getSurprise())
            .happiness(attribute.getEmotion().getHappiness())
            .glass(attribute.getGlass().getValue())
            .smile(attribute.getSmile().getValue())
            .intruder(intruder)
            .dateAccess(LocalDateTime.now())
            .image(image)
            .faceUser(StringUtils.isEmpty(faceId) ? null : FaceUserDto.builder().id(faceId).build())
            .build();
      attributeFaceRepository.save(attributesFaceMapper.toEntity(attributesFaceUserDto));
   }

}
