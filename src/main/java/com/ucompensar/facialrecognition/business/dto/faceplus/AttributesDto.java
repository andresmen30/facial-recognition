package com.ucompensar.facialrecognition.business.dto.faceplus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AttributesDto {

   private AgeDto age;

   private SmileDto smile;

   @JsonProperty("eyestatus")
   private EyestatusDto eyestatusDto;

   private EmotionDto emotion;

   private GlassDto glass;

   private FaceQuality facequality;

}
