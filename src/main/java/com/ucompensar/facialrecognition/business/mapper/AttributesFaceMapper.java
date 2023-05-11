package com.ucompensar.facialrecognition.business.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Import;

import com.ucompensar.facialrecognition.business.dto.faceuser.AttributesFaceDto;
import com.ucompensar.facialrecognition.infrastructure.entity.AttributesFace;

@Mapper(componentModel = "spring")
@Import({ LocalDateTime.class })
public interface AttributesFaceMapper {

   AttributesFace toEntity(final AttributesFaceDto attributesFaceDto);

   List<AttributesFaceDto> toDto(final List<AttributesFace> entity);

}
