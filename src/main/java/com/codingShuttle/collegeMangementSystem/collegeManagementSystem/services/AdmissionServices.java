package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services;


import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.AdmissionRecord;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Student;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.AdmissionRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.ProfessorRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.StudentRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmissionServices {

    //Here we write the main logic for the service Layer
    //The service layer interacts with its respective DTO and get the input data from the Presentation layer

    private final StudentRepository studentRepository;
    private final AdmissionRepository admissionRecordRepository;


    @Transactional
    public AdmissionRecord createAdmission(Integer fees, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        AdmissionRecord record = AdmissionRecord.builder()
                .fees(fees)
                .student(student)
                .build();

        return admissionRecordRepository.save(record);
    }

    public List<AdmissionRecord> getAllAdmissionRecords() {
        return admissionRecordRepository.findAll();
    }


}
