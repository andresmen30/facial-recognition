package com.ucompensar.facialrecognition.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucompensar.facialrecognition.business.dto.collegecareer.CollegeCareerDto;
import com.ucompensar.facialrecognition.business.mapper.CollegeCareerMapper;
import com.ucompensar.facialrecognition.infrastructure.entity.CollegeCareer;
import com.ucompensar.facialrecognition.infrastructure.repository.CollegeCareerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CollegeCareerServiceImpl implements CollegeCareerService {

   private final CollegeCareerRepository collegeCareerRepository;

   private final CollegeCareerMapper collegeCareerMapper;

   @Override
   public void save(final CollegeCareerDto collegeCareerDto) {
      collegeCareerRepository.save(collegeCareerMapper.toEntity(collegeCareerDto));
   }

   @Override
   public List<CollegeCareerDto> findAll() {
      return collegeCareerMapper.toDtoList(collegeCareerRepository.findAll());
   }

   @Override
   public List<CollegeCareerDto> findByFaculty(final String facultyId) {
      final List<CollegeCareer> collegeCareers = collegeCareerRepository.findByFaculty(facultyId);
      return collegeCareerMapper.toDtoList(collegeCareers);
   }

}
