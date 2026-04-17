package com.tech.jobApp.mapper;

import com.tech.jobApp.dto.response.JobApplicationResponseDto;
import com.tech.jobApp.model.JobApplication;

public class JobApplicationMapper {

    public static JobApplicationResponseDto toDto(JobApplication app) {

        JobApplicationResponseDto dto = new JobApplicationResponseDto();

        dto.setId(app.getId());
        dto.setApplicantName(app.getApplicantName());
        dto.setApplicantEmail(app.getApplicantEmail());
        dto.setStatus(app.getStatus());
        dto.setAppliedAt(app.getAppliedAt());

        dto.setJobTitle(app.getJob().getTitle());
        dto.setCompanyName(app.getJob().getCompany().getName());

        return dto;
    }
}