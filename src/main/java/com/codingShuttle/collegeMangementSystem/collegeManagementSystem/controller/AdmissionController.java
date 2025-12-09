package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.controller;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.AdmissionRecord;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.AdmissionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/call/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionServices admissionService;

    @PostMapping("/StudentID/{studentId}/fees/{fees}")
    public AdmissionRecord createAdmission(@PathVariable Long studentId, @PathVariable Integer fees) {
        return admissionService.createAdmission(fees, studentId);
    }

    @GetMapping(path = "/getAllAdmissionRecords")
    public List<AdmissionRecord> getAllAdmissionRecords() {
        return admissionService.getAllAdmissionRecords();
    }


}