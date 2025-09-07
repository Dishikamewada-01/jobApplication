package com.tech.jobApp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.jobApp.dto.request.JobCreateDto;
import com.tech.jobApp.dto.request.JobUpdateDto;
import com.tech.jobApp.dto.response.JobDto;

import com.tech.jobApp.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // Get all jobs (admin + user)
    @GetMapping
    public ResponseEntity<List<JobDto>> getAllJobs(
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
    	
        List<JobDto> jobs = jobService.getAllJobs(page , size);
        return ResponseEntity.ok(jobs);
    }

    // Get job by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) {
    	JobDto jobDto = jobService.getJobById(id);
        return ResponseEntity.ok(jobDto);
    }

    
    // Create Job
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JobDto> createJob(@Valid @RequestBody JobCreateDto jobCreateDto) {
        JobDto createdJob = jobService.createJob(jobCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }

    // Update job (admin)
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<JobDto> updateJobPartially(@PathVariable Long id, @Valid @RequestBody JobUpdateDto requestedJob) {
    	
        return ResponseEntity.ok(jobService.updateJob(id, requestedJob));
    }

    // Delete job (admin)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully.");
    }

    // Search job by title
    @GetMapping("/search/title")
    public ResponseEntity<List<JobDto>> searchByTitle(@RequestParam String title , @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
    	return ResponseEntity.ok(jobService.searchJobsByTitle(title , page , size));
    }

    // Search job by location
    @GetMapping("/search/location")
    public ResponseEntity<List<JobDto>> searchByLocation(@RequestParam String location , @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
    	return ResponseEntity.ok(jobService.searchJobsByLocation(location , page , size));
    }

    // Search job by company
    @GetMapping("/search/company")
    public ResponseEntity<List<JobDto>> searchByCompany(@RequestParam String companyName , @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(jobService.searchJobsByCompanyName(companyName , page , size));
    }
    
}
