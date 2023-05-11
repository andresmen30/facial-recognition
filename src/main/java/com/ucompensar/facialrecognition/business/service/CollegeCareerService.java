package com.ucompensar.facialrecognition.business.service;

import java.util.List;

import com.ucompensar.facialrecognition.business.dto.collegecareer.CollegeCareerDto;

public interface CollegeCareerService {

   void save(final CollegeCareerDto collegeCareerDto);

   List<CollegeCareerDto> findAll();

   List<CollegeCareerDto> findByFaculty(final String facultyId);

}
