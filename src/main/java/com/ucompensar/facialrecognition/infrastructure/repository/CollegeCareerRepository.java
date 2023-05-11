package com.ucompensar.facialrecognition.infrastructure.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ucompensar.facialrecognition.infrastructure.entity.CollegeCareer;

public interface CollegeCareerRepository extends MongoRepository<CollegeCareer, String> {

   @Query("{'faculty.id' : :#{#facultyId}}")
   List<CollegeCareer> findByFaculty(@Param("facultyId") final String facultyId);

}
