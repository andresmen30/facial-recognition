package com.ucompensar.facialrecognition.business.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ucompensar.facialrecognition.business.dto.faceuser.AttributesFaceDto;
import com.ucompensar.facialrecognition.infrastructure.entity.AttributesFace;
import com.ucompensar.facialrecognition.util.FaceUtil;

@Mapper(componentModel = "spring", imports = { LocalDateTime.class, FaceUtil.class })
public interface AttributesFaceMapper {

   @Mapping(expression = "java(FaceUtil.emotion(entity))", target = "detectedEmotion")
   AttributesFaceDto toEntity(final AttributesFace entity);

   AttributesFace toEntity(final AttributesFaceDto dto);

   List<AttributesFaceDto> toDto(final List<AttributesFace> entity);

}
