package com.ucompensar.facialrecognition.business.service;

import java.util.List;

import com.ucompensar.facialrecognition.business.dto.faculty.FacultyDto;

public interface FacultyService {

   void save(final FacultyDto facultyDto);

   List<FacultyDto> findAll();

   FacultyDto findById(final String id);

}
