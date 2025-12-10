package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
