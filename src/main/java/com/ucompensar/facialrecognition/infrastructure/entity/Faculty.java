package com.ucompensar.facialrecognition.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Document(collection = "facultys")
@Builder
@Data
@ToString
public class Faculty {

   @Id
   private String id;

   private String name;

}
