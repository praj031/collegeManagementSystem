package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Professor;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Subject;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.ProfessorRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServices {

    //Here we write the main logic for the service Layer

    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;

    @Transactional
    public Subject createSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    @Transactional
    public Subject updateSubject(Long id, Subject subject) {
        Subject existing = subjectRepository.findById(id).orElseThrow();
        // Update fields
        return subjectRepository.save(existing);
    }

    @Transactional
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    // Business Logic
    @Transactional
    public void assignProfessorToSubject(Long subjectId, Long professorId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        Professor professor = professorRepository.findById(professorId).orElseThrow();
        subject.setProfessor(professor);
        professor.getSubjects().add(subject);
        subjectRepository.save(subject);
    }

}
