package com.tech.jobApp.mapper;

import com.tech.jobApp.dto.response.CompanyBasicDto;
import com.tech.jobApp.dto.response.JobDto;
import com.tech.jobApp.model.Job;

public class JobMapper {

	public static JobDto mapJobToDto(Job job) {
        JobDto dto = new JobDto();
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setMinSalary(job.getMinSalary());
        dto.setMaxSalary(job.getMaxSalary());
        dto.setPostedAt(job.getPostedAt());

        if (job.getCompany() != null) {
            CompanyBasicDto companyDto = new CompanyBasicDto();
            companyDto.setName(job.getCompany().getName());
            companyDto.setType(job.getCompany().getType());
            // No jobs set inside companyDto
            dto.setCompany(companyDto);
        }
        return dto;
    }
}
