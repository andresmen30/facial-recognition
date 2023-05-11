package com.ucompensar.facialrecognition.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Document(collection = "faceUsers")
@Data
@Builder
@ToString
public class FaceUser {

   @Id
   private String id;

   private String faceToken;

   @DBRef
   private PersonalInformation personalInformation;

}
