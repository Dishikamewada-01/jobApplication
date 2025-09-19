package com.tech.jobApp.service;

import java.util.List;

import com.tech.jobApp.dto.request.JobCreateDto;
import com.tech.jobApp.dto.request.JobUpdateDto;
import com.tech.jobApp.dto.response.JobDto;

public interface JobService {
	
	    // Create or Post Job
	    JobDto createJob(JobCreateDto jobCreateDto);

	    // Update an existing job
	    JobDto updateJob(Long id, JobUpdateDto jobUpdateDto);

	    // Delete job
	    void deleteJob(Long id);

	    //Get all the Jobs
	    List<JobDto> getAllJobs(int page , int size);

	    // Get Job By id
	    JobDto getJobById(Long id);

	    // Search Job by uts title
	    List<JobDto> searchJobsByTitle(String title , int page , int size);

	    // Search Job by location
	    List<JobDto> searchJobsByLocation(String location , int page , int size);

	    // Search job by particular company name
	    List<JobDto> searchJobsByCompanyName(String companyName , int page , int size);

}
