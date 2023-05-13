package com.ucompensar.facialrecognition.business.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ucompensar.facialrecognition.business.dto.faceuser.AttributesFaceDto;
import com.ucompensar.facialrecognition.business.mapper.AttributesFaceMapper;
import com.ucompensar.facialrecognition.infrastructure.entity.AttributesFace;
import com.ucompensar.facialrecognition.infrastructure.repository.AttributeFaceRepository;
import com.ucompensar.facialrecognition.infrastructure.repository.AttributeFaceRepositoryCriteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttributeFaceServiceImpl implements AttributeFaceService {

   private final AttributeFaceRepository attributeFaceRepository;

   private final AttributeFaceRepositoryCriteria attributeFaceRepositoryCriteria;

   private final AttributesFaceMapper attributesFaceMapper;

   @Override
   public List<AttributesFaceDto> getAll() {
      return attributesFaceMapper.toDto(attributeFaceRepository.findAll());
   }

   @Override
   public int getAllPaginatorCount() {
      return (int) attributeFaceRepositoryCriteria.count();

   }

   @Override
   public List<AttributesFaceDto> getAllPaginator(final PageRequest pageable) {
      final List<AttributesFace> attributesFaces = attributeFaceRepositoryCriteria.getAllPaginator(pageable);
      return attributesFaceMapper.toDto(attributesFaces);
   }

   @Override
   public void save(AttributesFaceDto attributesFaceDto) {
      attributeFaceRepository.save(attributesFaceMapper.toEntity(attributesFaceDto));
   }

}
