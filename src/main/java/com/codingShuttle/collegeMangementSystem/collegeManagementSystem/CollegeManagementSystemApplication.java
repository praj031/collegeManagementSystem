package com.codingShuttle.collegeMangementSystem.collegeManagementSystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CollegeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeManagementSystemApplication.class, args);
        //This will have all the changes related to college management system project
        // Repo name : https://github.com/praj031/collegeManagementSystem.git

        /*
        1. One professor that can teach many subject -- Onetomany mapping relationship
        2. One professor can teach multiple student -- manytomany mapping relationship
        3. One student can learn for multiple professor -- onetomany relationship
        4. One student have one admisson record -- OnetoOne mapping relationship
        5. One student can learn multiple subject --




         */





	}


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
