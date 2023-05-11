package com.ucompensar.facialrecognition.business.dto.faceplus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EyestatusDto {

   @JsonProperty("left_eye_status")
   private EyeSingleDto leftEyeStatus;

   @JsonProperty("right_eye_status")
   private EyeSingleDto RightEyeStatus;

}
