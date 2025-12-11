package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.LoginDTO;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.SignUpDTO;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.UserDto;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.User;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.exception.ResourceNotFoundException;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    //This is the service layer where all the logic will be written..
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with email "+username+" not found"));
    }
    //User service is used to create a new user and then store it in the DB, so that later we can authenticate it .
    public UserDto signUp(SignUpDTO signUpDto) {
        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent()) {
            throw new BadCredentialsException("User with email already exits "+ signUpDto.getEmail());
        }

        User toBeCreatedUser = modelMapper.map(signUpDto, User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword())); //Getting the password and encoding it .

        User savedUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }




}
