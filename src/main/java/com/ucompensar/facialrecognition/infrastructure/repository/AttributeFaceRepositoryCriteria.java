package com.ucompensar.facialrecognition.infrastructure.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ucompensar.facialrecognition.infrastructure.entity.AttributesFace;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AttributeFaceRepositoryCriteria {

   private final MongoTemplate mongoTemplate;

   public long count() {
      final Query query = new Query();
      return mongoTemplate.count(query, AttributesFace.class);
   }

   public List<AttributesFace> getAllPaginator(final PageRequest pageable) {
      final Query query = new Query().with(Sort.by(Sort.Direction.DESC, "dateAccess"));
      return mongoTemplate.find(query.with(pageable), AttributesFace.class);

   }

}
