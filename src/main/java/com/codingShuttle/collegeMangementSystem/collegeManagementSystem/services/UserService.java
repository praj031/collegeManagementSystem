package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.exception.ResourceNotFoundException;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    //This is the service layer where all the logic will be written..
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with email "+username+" not found"));
    }
}
