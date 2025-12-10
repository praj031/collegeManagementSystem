package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
