package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdmissionRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer fees;

    //Since there is one to one mapping record of student
    @OneToOne
    private Student student;

}
