package com.ucompensar.facialrecognition.business.mapper;

import org.mapstruct.Mapper;

import com.ucompensar.facialrecognition.business.dto.faceuser.PersonalInformationDto;
import com.ucompensar.facialrecognition.infrastructure.entity.PersonalInformation;

@Mapper(componentModel = "spring")
public interface PersonalInformationMapper {

   PersonalInformation toEntity(final PersonalInformationDto dto);

}
