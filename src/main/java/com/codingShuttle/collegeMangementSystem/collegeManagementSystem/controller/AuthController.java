package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.controller;


import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.LoginDTO;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.LoginResponseDTO;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.SignUpDTO;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.UserDto;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.UserRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.AuthService;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    //private final UserRepository userRepository;
    @Autowired
    private final UserService userService;
    private final AuthService authService;
    //private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDTO signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    //There is another use case lets say customer pases the cookie rather than the username or password.
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO,
                                        HttpServletRequest request,
                                        HttpServletResponse response){

       LoginResponseDTO loginResponseDTO = authService.login(loginDTO);

        Cookie cookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true); //This cookie does not get exposed.
        //cookie.setSecure(true);// By doing this we make our cookies secures
        response.addCookie(cookie);
        request.getCookies();


        return ResponseEntity.ok(loginResponseDTO);
        //Access token is short-lived while refresh token is long-lived.
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest httpServletRequest){
        String refreshToken = Arrays.stream(httpServletRequest.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies") );

        LoginResponseDTO loginResponseDTO = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDTO);
    }



}
