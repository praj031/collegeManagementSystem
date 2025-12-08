package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 100)
    private String title;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    // Many-to-many students
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students = new ArrayList<>();

}


