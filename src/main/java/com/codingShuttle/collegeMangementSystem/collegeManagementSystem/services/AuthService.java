package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.LoginDTO;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final  JwtService jwtService;


    //After we create the user, when we log-in we will fetch the value from the DB,
    //Now when we fetch the value from the DB we need to check the password and this will be done by the Authentication Manager and Authenticator.
    //So, now we are using bean of @Authentication to call in the authentication manager and decode it.
    public String login(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail() , loginDTO.getPassword()));

        User user = (User) authentication.getPrincipal();
        //String token = jwtService.generateToken(user);
        return jwtService.generateToken(user);
    }


}
