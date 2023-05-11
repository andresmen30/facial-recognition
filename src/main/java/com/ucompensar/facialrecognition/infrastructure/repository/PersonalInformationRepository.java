package com.ucompensar.facialrecognition.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ucompensar.facialrecognition.infrastructure.entity.PersonalInformation;

public interface PersonalInformationRepository extends MongoRepository<PersonalInformation, String> {

}
