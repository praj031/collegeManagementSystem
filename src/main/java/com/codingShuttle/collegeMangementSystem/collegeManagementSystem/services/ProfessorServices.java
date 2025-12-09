package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services;


import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Professor;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Student;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Subject;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.ProfessorRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.StudentRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorServices {

    //Here we write the main logic for the service Layer

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public Professor createProfessor(Professor professor){
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor assignProfessorToSubject(Long professorId, Long subjectId) {

        Professor professor = professorRepository.findById(professorId).orElseThrow();

        Subject subject = subjectRepository.findById(subjectId).orElseThrow();

        professor.getSubjects().add(subject);
        subject.getProfessor().add(professor);

        subjectRepository.save(subject);

        return professor;
    }

    //TO get all the professor
    public List<Professor> listOfProfessors() {
        return professorRepository.findAll();
    }



}
