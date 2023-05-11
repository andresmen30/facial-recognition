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
public class FaceRectangleDto {

   @JsonProperty("top")
   private Integer top;

   @JsonProperty("left")
   private Integer left;

   @JsonProperty("width")
   private Integer width;

   @JsonProperty("height")
   private Integer height;

}
