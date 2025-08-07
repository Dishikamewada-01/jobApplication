package com.tech.jobApp.service;

import java.util.List;

import com.tech.jobApp.dto.response.JobDto;
import com.tech.jobApp.model.Job;

public interface JobService {
	
	    Job createJob(Job job);

	    Job updateJob(Long id, Job job);

	    void deleteJob(Long id);

	    List<JobDto> getAllJobs();

	    JobDto getJobById(Long id);

	    List<JobDto> searchJobsByTitle(String title);

	    List<JobDto> searchJobsByLocation(String location);

	    List<JobDto> searchJobsByCompanyName(String companyName);

}
