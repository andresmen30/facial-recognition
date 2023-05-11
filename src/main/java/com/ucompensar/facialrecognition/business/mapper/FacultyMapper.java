package com.ucompensar.facialrecognition.business.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ucompensar.facialrecognition.business.dto.faculty.FacultyDto;
import com.ucompensar.facialrecognition.infrastructure.entity.Faculty;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

   Faculty toEntity(final FacultyDto facultyDto);

   FacultyDto toDto(final Faculty entity);

   List<FacultyDto> toDtoList(final List<Faculty> listEntity);

}
