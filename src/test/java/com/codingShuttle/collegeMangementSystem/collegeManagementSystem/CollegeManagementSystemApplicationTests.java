package com.codingShuttle.collegeMangementSystem.collegeManagementSystem;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.User;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CollegeManagementSystemApplicationTests {



    @Autowired
    private JwtService jwtService;

    @Test
    void contextLoads() {

        //Firstly we ar epassing the data of the user
        User user = new User(4l, "pritish@gmail.com", "1234");
        //Getting the generated tokan
        String token = jwtService.generateAccessToken(user);
        //Since we have  id as Long, we are making it as Long
        Long id = jwtService.getUserIdFromTokan(token);
        //Printing the tokan
        System.out.println("Tokan = "+token);
        System.out.println("ID = "+ id);

    }

}
