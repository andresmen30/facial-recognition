package com.ucompensar.facialrecognition.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ucompensar.facialrecognition.infrastructure.entity.AttributesFace;

public interface AttributeFaceRepository extends MongoRepository<AttributesFace, String> {

}
