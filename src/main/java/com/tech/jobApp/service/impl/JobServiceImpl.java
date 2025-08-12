package com.tech.jobApp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.jobApp.dto.request.JobUpdateDto;
import com.tech.jobApp.dto.response.JobDto;
import com.tech.jobApp.mapper.JobMapper;
import com.tech.jobApp.model.Company;
import com.tech.jobApp.model.Job;
import com.tech.jobApp.repository.CompanyRepository;
import com.tech.jobApp.repository.JobRepository;
import com.tech.jobApp.service.JobService;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private CompanyRepository companyRepository;
    
    
    // Create job
    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    
    //Update job
    @Override
    public JobDto updateJob(Long id, JobUpdateDto jobUpdateDto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + id));

        if (jobUpdateDto.getTitle() != null) {
            job.setTitle(jobUpdateDto.getTitle());
        }
        if (jobUpdateDto.getDescription() != null) {
            job.setDescription(jobUpdateDto.getDescription());
        }
        if (jobUpdateDto.getLocation() != null) {
            job.setLocation(jobUpdateDto.getLocation());
        }
        if (jobUpdateDto.getMinSalary() != null) {
            job.setMinSalary(jobUpdateDto.getMinSalary());
        }
        if (jobUpdateDto.getMaxSalary() != null) {
            job.setMaxSalary(jobUpdateDto.getMaxSalary());
        }
        if (jobUpdateDto.getCompanyId() != null) {
            Company company = companyRepository.findById(jobUpdateDto.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found with ID: " + jobUpdateDto.getCompanyId()));
            job.setCompany(company);
        }

        Job updatedJob = jobRepository.save(job);
        return JobMapper.mapJobToDto(updatedJob);
    }


    
    //Delete job
    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + id));
        jobRepository.delete(job);
    }

    
 // Get All Job
    @Override
    public List<JobDto> getAllJobs() {
    	List<Job> jobs = jobRepository.findAll();
        List<JobDto> jobDtos = new ArrayList<>();

        for (Job job : jobs) {
            JobDto dto = JobMapper.mapJobToDto(job);
            jobDtos.add(dto);
        }

        return jobDtos;
    }

    
    // Get Job with id
    @Override
    public JobDto getJobById(Long id) {
    	Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            return JobMapper.mapJobToDto(job);
        }
        return null;
    }

    
    // Search Job By Title
    @Override
    public List<JobDto> searchJobsByTitle(String title) {
    	List<Job> jobs = jobRepository.findByTitleContainingIgnoreCase(title);
        List<JobDto> jobDtos = new ArrayList<>();
        for (Job job : jobs) {
            jobDtos.add(JobMapper.mapJobToDto(job));
        }
        return jobDtos;
    }

    
    //Search job by Location
    @Override
    public List<JobDto> searchJobsByLocation(String location) {
    	List<Job> jobs = jobRepository.findByLocationContainingIgnoreCase(location);
        List<JobDto> jobDtos = new ArrayList<>();
        for (Job job : jobs) {
            jobDtos.add(JobMapper.mapJobToDto(job));
        }
        return jobDtos;
    	
    }

    
    // Search job By Company Name
    @Override
    public List<JobDto> searchJobsByCompanyName(String companyName) {
    	List<Job> jobs = jobRepository.findByCompany_NameIgnoreCase(companyName);
        List<JobDto> jobDtos = new ArrayList<>();
        for (Job job : jobs) {
            jobDtos.add(JobMapper.mapJobToDto(job));
        }
        return jobDtos;
    }

    
}
