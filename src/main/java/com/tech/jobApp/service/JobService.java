package com.tech.jobApp.service;

import java.util.List;

import com.tech.jobApp.dto.request.JobUpdateDto;
import com.tech.jobApp.dto.response.JobDto;
import com.tech.jobApp.model.Job;

public interface JobService {
	
	    // Create or Post Job
	    Job createJob(Job job);

	    // Update an existing job
	    JobDto updateJob(Long id, JobUpdateDto jobUpdateDto);

	    // Delete job
	    void deleteJob(Long id);

	    //Get all the Jobs
	    List<JobDto> getAllJobs();

	    // Get Job By id
	    JobDto getJobById(Long id);

	    // Search Job by uts title
	    List<JobDto> searchJobsByTitle(String title);

	    // Search Job by location
	    List<JobDto> searchJobsByLocation(String location);

	    // Search job by particular company name
	    List<JobDto> searchJobsByCompanyName(String companyName);

}
