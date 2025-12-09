package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    @ManyToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();
}
