package com.ucompensar.facialrecognition.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Document(collection = "collegeCareers")
@Data
@ToString
public class CollegeCareer {

   @Id
   private String id;

   private String name;

   @DBRef
   private Faculty faculty;

}
