package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.controller;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Professor;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.ProfessorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/call/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorServices professorService;

    @PostMapping(path = "/createProfessor")
    public Professor create(@RequestBody Professor professor) {
        return professorService.createProfessor(professor);
    }

    @PostMapping("/{professorId}/subjects/{subjectId}")
    public Professor assignSubject(@PathVariable Long professorId, @PathVariable Long subjectId) {
        return professorService.assignProfessorToSubject(professorId, subjectId);
    }

    @GetMapping(path = "/listOfProfessors")
    public List<Professor> listOfProfessors() {
        return professorService.listOfProfessors();
    }
}