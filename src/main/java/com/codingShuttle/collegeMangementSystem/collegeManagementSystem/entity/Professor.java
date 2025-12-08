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
public class Professor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();
    @ManyToMany(mappedBy = "professors")
    private List<Student> students = new ArrayList<>();
}
