package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {

    //This is the Professor repository


}
