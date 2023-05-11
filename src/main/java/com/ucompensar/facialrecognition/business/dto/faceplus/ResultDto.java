package com.ucompensar.facialrecognition.business.dto.faceplus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultDto {

   private Double confidence;

   private AttributesDto attributes;

   @JsonProperty("user_id")
   private String userId;

   @JsonProperty("face_token")
   private String faceToken;

}
