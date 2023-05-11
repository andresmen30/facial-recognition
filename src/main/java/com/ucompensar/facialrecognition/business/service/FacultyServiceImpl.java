package com.ucompensar.facialrecognition.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucompensar.facialrecognition.business.dto.faculty.FacultyDto;
import com.ucompensar.facialrecognition.business.mapper.FacultyMapper;
import com.ucompensar.facialrecognition.infrastructure.repository.FacultyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

   private final FacultyRepository facultyRepository;

   private final FacultyMapper facultyMapper;

   @Override
   public void save(final FacultyDto facultyDto) {
      facultyRepository.save(facultyMapper.toEntity(facultyDto));
   }

   @Override
   public List<FacultyDto> findAll() {
      return facultyMapper.toDtoList(facultyRepository.findAll());
   }

   @Override
   public FacultyDto findById(final String id) {
      return facultyMapper.toDto(facultyRepository.findById(id).orElse(null));
   }

}
