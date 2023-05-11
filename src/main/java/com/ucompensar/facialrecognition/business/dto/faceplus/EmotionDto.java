package com.ucompensar.facialrecognition.business.dto.faceplus;

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
public class EmotionDto {

   private Double anger;

   private Double disgust;

   private Double fear;

   private Double happiness;

   private Double neutral;

   private Double sadness;

   private Double surprise;

}
