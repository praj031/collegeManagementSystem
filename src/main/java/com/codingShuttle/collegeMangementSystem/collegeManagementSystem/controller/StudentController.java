package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.controller;


import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Student;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/call/students")
@RequiredArgsConstructor
public class StudentController {

    // Calling the service layer to fetch in student services
    private final StudentServices studentServices;

    @PostMapping(path = "/createStudent")
    public Student createStudent(@RequestBody Student student) {
        return studentServices.createStudent(student);
    }

    @PostMapping("/{studentId}/professors/{professorId}")
    public Student assignProfessor(@PathVariable Long studentId, @PathVariable Long professorId) {
        return studentServices.assignStudentToProfessor(studentId, professorId);
    }

    @PostMapping("/{studentId}/subjects/{subjectId}")
    public Student assignSubject(@PathVariable Long studentId, @PathVariable Long subjectId) {
        return studentServices.assignStudentToSubject(studentId, subjectId);
    }

    @GetMapping(path = "/listofStudent")
    public List<Student> listOfStudents() {
        return studentServices.listOfStudents();
    }


}
