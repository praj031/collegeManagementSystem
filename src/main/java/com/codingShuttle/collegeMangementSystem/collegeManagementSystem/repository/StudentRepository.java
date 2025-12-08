package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Student;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {


    //This is the student repository

}
