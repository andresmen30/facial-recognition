package com.ucompensar.facialrecognition.business.dto.faceplus;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DetectDto {

   @JsonProperty("request_id")
   private String requestId;

   @JsonProperty("time_used")
   private Integer timeUsed;

   @JsonProperty("faces")
   private List<FaceDto> faces;

   @JsonProperty("image_id")
   private String imageId;

   @JsonProperty("face_num")
   private Integer faceNum;

}
