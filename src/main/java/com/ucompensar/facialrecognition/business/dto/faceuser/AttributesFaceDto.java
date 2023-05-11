package com.ucompensar.facialrecognition.business.dto.faceuser;

import java.time.LocalDateTime;

import com.ucompensar.facialrecognition.util.enums.DecisionEnum;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class AttributesFaceDto {

   private String id;

   private LocalDateTime dateAccess;

   private String image;

   private Double anger;

   private Double disgust;

   private Double fear;

   private Double neutral;

   private Double sadness;

   private Double surprise;

   private Double happiness;

   private String glass;

   private Double smile;

   private DecisionEnum intruder;

   private FaceUserDto faceUser;

}
