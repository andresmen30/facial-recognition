package com.ucompensar.facialrecognition.business.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.ucompensar.facialrecognition.business.dto.faceuser.AttributesFaceDto;

public interface AttributeFaceService {

   int getAllPaginatorCount();

   List<AttributesFaceDto> getAll();

   List<AttributesFaceDto> getAllPaginator(final PageRequest pageable);

   void save(final AttributesFaceDto attributesFaceDto);

}
