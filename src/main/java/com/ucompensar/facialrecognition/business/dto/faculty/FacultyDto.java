package com.ucompensar.facialrecognition.business.dto.faculty;

import java.util.List;

import com.ucompensar.facialrecognition.business.dto.collegecareer.CollegeCareerDto;

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
public class FacultyDto {

   private String id;

   private String name;

   private List<CollegeCareerDto> collegeCareers;

}
