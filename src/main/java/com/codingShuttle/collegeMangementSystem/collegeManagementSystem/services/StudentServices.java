package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services;


import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.AdmissionRecord;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Professor;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Student;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.Subject;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.AdmissionRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.ProfessorRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.StudentRepository;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServices {

    //Here we write the main logic for the service Layer

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    private final AdmissionRepository admissionRecordRepository;

    @Transactional
    public Student createStudent(Student student){

        return studentRepository.save(student);

    }

    @Transactional
    public Student assignProfessorToStudent(Long studentid, Long professor_id){

        //Since there is student and professor relation need to include both to get the data.
        Student student = studentRepository.findById(studentid).orElseThrow();
        Professor professor = professorRepository.findById(professor_id).orElseThrow();

        student.getProfessors().add(professor);
        professor.getStudents().add(student);

        professorRepository.save(professor);
        return studentRepository.save(student);

    }

    @Transactional
    public Student assignSubjectToStudent(Long studentid, Long subjectId){

        //Since there is student and subject relation need to include both to get the data.
        Student student = studentRepository.findById(studentid).orElseThrow();
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();

        student.getSubjects().add(subject);
        subject.getStudents().add(student);

        subjectRepository.save(subject);
        return studentRepository.save(student);

    }

    @Transactional
    public AdmissionRecord createAdmissionRecordForStudent(Long studentId, AdmissionRecord record){

        //Since there is student and admissionRecord relation need to include both to get the data.
        Student student = studentRepository.findById(studentId).orElseThrow();
        //For each record there will be one student
        record.setStudent(student);
        record = admissionRecordRepository.save(record);
        student.setAdmissionRecord(record);
        return record;
    }

}
