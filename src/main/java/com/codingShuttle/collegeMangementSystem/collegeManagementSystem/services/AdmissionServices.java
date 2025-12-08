package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services;


import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Student;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.AdmissionRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.ProfessorRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.StudentRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.SubjectRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmissionServices {

    //Here we write the main logic for the service Layer
    //The service layer interacts with it's respective DTO and get the input data from the Presentation layer

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    private final AdmissionRepository admissionRecordRepository;




}
