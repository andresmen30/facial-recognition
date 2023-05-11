package com.ucompensar.facialrecognition.business.dto.faceplus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EyeSingleDto {

   @JsonProperty("no_glass_eye_open")
   private Double noGlassEyeOpen;

   @JsonProperty("no_glass_eye_close")
   private Double noGlassEyeClose;

   @JsonProperty("normal_glass_eye_open")
   private Double normalGlassEyeOpen;

   @JsonProperty("normal_glass_eye_close")
   private Double normalGlassEyeClose;

}
