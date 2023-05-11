package com.ucompensar.facialrecognition.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ucompensar.facialrecognition.infrastructure.entity.Faculty;

public interface FacultyRepository extends MongoRepository<Faculty, String> {

}
