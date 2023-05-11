package com.ucompensar.facialrecognition.business.dto.collegecareer;

import com.ucompensar.facialrecognition.business.dto.faculty.FacultyDto;

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
public class CollegeCareerDto {

   private String id;

   private String name;

   private FacultyDto faculty;

}
