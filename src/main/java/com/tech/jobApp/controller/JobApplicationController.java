package com.tech.jobApp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.tech.jobApp.mapper.JobApplicationMapper;
import com.tech.jobApp.model.JobApplication;
import com.tech.jobApp.service.JobApplicationService;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService applicationService;

    // ✅ Apply for Job API
    @PostMapping("/apply/{jobId}")
    public ResponseEntity<?> applyForJob(
            @PathVariable Long jobId,
            @RequestParam("file") MultipartFile file,
            @RequestParam String name,
            @RequestParam String email) {

        try {
            JobApplication application = applicationService.applyForJob(jobId, file, name, email);

            return ResponseEntity.ok(JobApplicationMapper.toDto(application)); // ✅ FIX

        } catch (RuntimeException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}