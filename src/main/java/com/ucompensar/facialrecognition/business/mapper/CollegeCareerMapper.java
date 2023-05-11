package com.ucompensar.facialrecognition.business.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ucompensar.facialrecognition.business.dto.collegecareer.CollegeCareerDto;
import com.ucompensar.facialrecognition.infrastructure.entity.CollegeCareer;

@Mapper(componentModel = "spring")
public interface CollegeCareerMapper {

   CollegeCareer toEntity(final CollegeCareerDto collegeCareerDto);

   List<CollegeCareerDto> toDtoList(final List<CollegeCareer> listEntity);

}
