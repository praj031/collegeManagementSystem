package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepository extends JpaRepository<AdmissionRecord,Long> {

    //Repository to create the Admission Repository

}
