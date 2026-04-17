package com.tech.jobApp.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.tech.jobApp.model.JobApplication;

public interface JobApplicationService {

    JobApplication applyForJob(Long jobId, MultipartFile file, String name, String email) throws IOException;
}