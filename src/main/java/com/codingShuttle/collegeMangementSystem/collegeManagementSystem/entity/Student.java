package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Many-to-many relation between Students and Professors entity
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "student_professor",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professors = new ArrayList<>();

    // Many-to-many relationship between Students and Subjects
    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects = new ArrayList<>();

    // One-to-one with AdmissionRecord
    @OneToOne
    @JoinColumn(name = "admission_record_id")
    private AdmissionRecord admissionRecord;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
}
