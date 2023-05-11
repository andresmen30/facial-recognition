package com.ucompensar.facialrecognition.business.dto.faceplus;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchDto {

   @JsonProperty("image_id")
   private String imageId;

   private List<FaceDto> faces;

   private List<ResultDto> results;

}
