package com.ucompensar.facialrecognition.business.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ucompensar.facialrecognition.business.dto.faceuser.FaceUserDto;
import com.ucompensar.facialrecognition.infrastructure.entity.FaceUser;

@Mapper(componentModel = "spring")
public interface FaceUserMapper {

   FaceUser toEntity(final FaceUserDto faceUserDto);

   List<FaceUserDto> toDtoList(final List<FaceUser> listEntity);

}
