package com.ucompensar.facialrecognition.business.dto.faceuser;

import com.ucompensar.facialrecognition.business.dto.collegecareer.CollegeCareerDto;
import com.ucompensar.facialrecognition.business.dto.faculty.FacultyDto;
import com.ucompensar.facialrecognition.util.enums.GenderEnum;
import com.ucompensar.facialrecognition.util.enums.IdTypeEnum;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PersonalInformationDto {

   private String id;

   private String email;

   private String identificationNumber;

   private String firstName;

   private String secondName;

   private String surName;

   private String secondSurname;

   private String address;

   private Integer age;

   private GenderEnum gender;

   private IdTypeEnum idType;

   private CollegeCareerDto collageCareer;

   private FacultyDto faculty;

}
