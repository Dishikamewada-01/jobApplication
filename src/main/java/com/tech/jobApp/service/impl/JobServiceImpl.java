package com.tech.jobApp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tech.jobApp.dto.request.JobCreateDto;
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
    
    
    // Create Job
    @Override
    public JobDto createJob(JobCreateDto jobCreateDto) {
        // 1. Fetch company
        Company company = companyRepository.findById(jobCreateDto.getCompanyId())
            .orElseThrow(() -> new RuntimeException("Company not found with ID: " + jobCreateDto.getCompanyId()));

        // 2. Map DTO to Job entity
        Job job = new Job();
        job.setTitle(jobCreateDto.getTitle());
        job.setDescription(jobCreateDto.getDescription());
        job.setLocation(jobCreateDto.getLocation());
        job.setMinSalary(jobCreateDto.getMinSalary());
        job.setMaxSalary(jobCreateDto.getMaxSalary());
        job.setCompany(company);

        // 3. Save job
        Job savedJob = jobRepository.save(job);

        // 4. Map to DTO
        return JobMapper.mapJobToDto(savedJob);
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
    public List<JobDto> getAllJobs(int page , int size) {
    	Pageable pageable = PageRequest.of(page, size);
    	
    	Page<Job> jobsPage = jobRepository.findAll(pageable);
    	
        List<JobDto> jobDtos = new ArrayList<>();

        for (Job job : jobsPage.getContent()) {
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
    public List<JobDto> searchJobsByTitle(String title ,int page , int size) {
    	
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Job> jobsPage = jobRepository.findByTitleContainingIgnoreCase(title , pageable);
        List<JobDto> jobDtos = new ArrayList<>();
        for (Job job : jobsPage.getContent()) {
            jobDtos.add(JobMapper.mapJobToDto(job));
        }
        return jobDtos;
    }

    
    //Search job by Location
    @Override
    public List<JobDto> searchJobsByLocation(String location , int page , int size) {
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Job> jobsPage = jobRepository.findByLocationContainingIgnoreCase(location , pageable);
        List<JobDto> jobDtos = new ArrayList<>();
        for (Job job : jobsPage.getContent()) {
            jobDtos.add(JobMapper.mapJobToDto(job));
        }
        return jobDtos;
    	
    }

    
    // Search job By Company Name
    @Override
    public List<JobDto> searchJobsByCompanyName(String companyName , int page , int size) {
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Job> jobsPage = jobRepository.findByCompany_NameIgnoreCase(companyName , pageable);
        List<JobDto> jobDtos = new ArrayList<>();
        for (Job job : jobsPage.getContent()) {
            jobDtos.add(JobMapper.mapJobToDto(job));
        }
        return jobDtos;
    }

    
}
