package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.controller;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Subject;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.SubjectServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/call/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectServices subjectService;

    @PostMapping(path = "/creteSubject")
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @DeleteMapping(path = "/deleteSubject/{id}")
    public void deleteSubject(@RequestParam Long id){

         subjectService.deleteSubject(id);

    }

    @GetMapping(path = "/listOfAllSubject")
    public List<Subject> listOfAllSubject() {
        return subjectService.listOfAllSubject();
    }



}