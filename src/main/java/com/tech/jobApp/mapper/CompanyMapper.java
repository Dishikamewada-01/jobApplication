package com.tech.jobApp.mapper;

import java.util.ArrayList;
import java.util.List;

import com.tech.jobApp.dto.response.CompanyBasicDto;
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
		            JobBasicDto jobBasicDto = new JobBasicDto();
		            jobBasicDto.setTitle(job.getTitle());
		            jobBasicDto.setDescription(job.getDescription());
		            jobBasicDto.setLocation(job.getLocation());
		            jobBasicDto.setMinSalary(job.getMinSalary());
		            jobBasicDto.setMaxSalary(job.getMaxSalary());
		            jobBasicDto.setPostedAt(job.getPostedAt());
		            // jobDto.setCompany(null);  // Important: avoid nested company
		            jobDtos.add(jobBasicDto);
		        }
		    }
		    comDto.setJobs(jobDtos);

		    return comDto;
		}
	
	
		// Company -> CompanyBasicDto (without jobs)
		public static CompanyBasicDto mapCompanyToBasicDto(Company company) {
			CompanyBasicDto basicDto = new CompanyBasicDto();
			basicDto.setName(company.getName());
			basicDto.setType(company.getType());
			return basicDto;
		}
	        
	      
	
}
