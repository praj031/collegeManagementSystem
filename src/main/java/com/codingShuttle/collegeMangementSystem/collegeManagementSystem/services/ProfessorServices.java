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
    public void assignProfessorToSubject(Long professorId, Long subjectId){

        Professor professor = professorRepository.findById(professorId).orElseThrow();
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();


        //Vice versa mapping b/w professor and subject
        //First professor will get the subject and then subject will be assigned to professor
        professor.getSubjects().add(subject);
        subject.setProfessor(professor);

        professorRepository.save(professor);

    }


    @Transactional
    public void assignStudent(Long professorId, Long studentId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow();

        Student student = studentRepository.findById(studentId)
                .orElseThrow();

        professor.getStudents().add(student);
        student.getProfessors().add(professor);

        professorRepository.save(professor);
    }


}
