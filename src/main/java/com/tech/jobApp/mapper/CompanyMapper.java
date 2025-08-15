package com.tech.jobApp.mapper;




import java.util.ArrayList;
import java.util.List;


import com.tech.jobApp.dto.response.CompanyDto;
import com.tech.jobApp.dto.response.JobBasicDto;
import com.tech.jobApp.dto.response.JobDto;
import com.tech.jobApp.model.Company;
import com.tech.jobApp.model.Job;


public class CompanyMapper {
	
	public static CompanyDto mapCompanyToDto(Company company) {
		 CompanyDto comDto = new CompanyDto();
	        comDto.setName(company.getName());
	        comDto.setType(company.getType());
	        
	        List<JobBasicDto> jobDtos = new ArrayList<>();
		    if (company.getJobs() != null) {
		        for (Job job : company.getJobs()) {
		            JobBasicDto jobDto = new JobBasicDto();
		            jobDto.setTitle(job.getTitle());
		            jobDto.setDescription(job.getDescription());
		            jobDto.setLocation(job.getLocation());
		            jobDto.setMinSalary(job.getMinSalary());
		            jobDto.setMaxSalary(job.getMaxSalary());
		            jobDto.setPostedAt(job.getPostedAt());
		            // jobDto.setCompany(null);  // Important: avoid nested company
		            jobDtos.add(jobDto);
		        }
		    }
		    comDto.setJobs(jobDtos);

		    return comDto;
		}
	        
	      
	
}
