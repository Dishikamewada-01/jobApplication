package com.tech.jobApp.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tech.jobApp.model.Job;
import com.tech.jobApp.model.JobApplication;
import com.tech.jobApp.model.Users;
import com.tech.jobApp.repository.JobApplicationRepository;
import com.tech.jobApp.repository.JobRepository;
import com.tech.jobApp.repository.UserRepository;
import com.tech.jobApp.service.JobApplicationService;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationRepository applicationRepo;

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public JobApplication applyForJob(Long jobId, MultipartFile file, String name, String email) throws IOException {

        // 1️⃣ Get logged-in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Users user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Fetch job
        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // 3️⃣ Prevent duplicate application
        applicationRepo.findByUserIdAndJobId(user.getId(), jobId)
                .ifPresent(a -> {
                    throw new RuntimeException("You have already applied for this job");
                });

        // 4️⃣ Validate file
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (!file.getContentType().equals("application/pdf")) {
            throw new RuntimeException("Only PDF files are allowed");
        }

        // 5️⃣ Save file
        String uploadDir = "uploads/";

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        // 6️⃣ Save application
        JobApplication app = new JobApplication();
        app.setApplicantName(name);
        app.setApplicantEmail(email);
        app.setResumePath(filePath.toString());
        app.setJob(job);
        app.setUser(user);

        return applicationRepo.save(app);
    }
}