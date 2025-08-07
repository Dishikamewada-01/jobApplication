package com.tech.jobApp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.jobApp.dto.response.JobDto;
import com.tech.jobApp.mapper.JobMapper;
import com.tech.jobApp.model.Job;
import com.tech.jobApp.repository.JobRepository;
import com.tech.jobApp.service.JobService;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    
    
    // Create job
    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    
    //Update job
    @Override
    public Job updateJob(Long id, Job updatedJob) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + id));

        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setLocation(updatedJob.getLocation());
        job.setMinSalary(updatedJob.getMinSalary());
        job.setMaxSalary(updatedJob.getMaxSalary());
        job.setCompany(updatedJob.getCompany());

        return jobRepository.save(job);
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