package com.ucompensar.facialrecognition.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ucompensar.facialrecognition.infrastructure.entity.FaceUser;

public interface FaceUserRepository extends MongoRepository<FaceUser, String> {

   @Query("{'faceToken' : :#{#faceToken}}")
   FaceUser findByFaceToken(@Param("faceToken") String faceToken);

}
