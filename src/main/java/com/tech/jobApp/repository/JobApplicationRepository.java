package com.tech.jobApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tech.jobApp.model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    Optional<JobApplication> findByUserIdAndJobId(Long userId, Long jobId);
}