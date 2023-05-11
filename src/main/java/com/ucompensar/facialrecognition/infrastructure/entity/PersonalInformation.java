package com.ucompensar.facialrecognition.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.ucompensar.facialrecognition.util.enums.GenderEnum;
import com.ucompensar.facialrecognition.util.enums.IdTypeEnum;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Document(collection = "personalInformation")
@Data
@Builder
@ToString
public class PersonalInformation {

   @Id
   private String id;

   private String identificationNumber;

   private String email;

   private String firstName;

   private String secondName;

   private String surName;

   private String secondSurname;

   private String address;

   private Integer age;

   @Indexed
   @Field(targetType = FieldType.STRING)
   private GenderEnum gender;

   @Indexed
   @Field(targetType = FieldType.STRING)
   private IdTypeEnum idType;

   @DBRef
   private Faculty faculty;

   @DBRef
   private CollegeCareer collageCareer;

   @DBRef
   private FaceUser faceUser;

}
