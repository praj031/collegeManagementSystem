package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.controller;


import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.LoginDTO;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.SignUpDTO;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dto.UserDto;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.UserRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.AuthService;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){

        String token = authService.login(loginDTO);
        return ResponseEntity.ok(token);

    }




}
