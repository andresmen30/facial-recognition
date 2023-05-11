package com.ucompensar.facialrecognition.business.dto.faceplus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FaceDto {

   @JsonProperty("face_token")
   private String faceToken;

   @JsonProperty("face_rectangle")
   private FaceRectangleDto faceRectangle;

   private AttributesDto attributes;

}
