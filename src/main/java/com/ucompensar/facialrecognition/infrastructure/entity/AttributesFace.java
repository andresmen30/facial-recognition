package com.ucompensar.facialrecognition.infrastructure.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.ucompensar.facialrecognition.util.enums.DecisionEnum;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
@Document(collection = "attributesFaces")
public class AttributesFace {

   @Id
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

   @Indexed
   @Field(targetType = FieldType.STRING, name = "intruder")
   private DecisionEnum intruder;

   @DBRef
   private FaceUser faceUser;

}
