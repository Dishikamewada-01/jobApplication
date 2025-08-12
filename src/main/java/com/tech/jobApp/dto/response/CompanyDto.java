package com.tech.jobApp.dto.response;


import java.util.List;

public class CompanyDto {

	private String name;
    private String type;
    private List<JobDto> jobs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<JobDto> getJobs() {
		return jobs;
	}
	public void setJobs(List<JobDto> jobs) {
		this.jobs = jobs;
	}
	public CompanyDto() {
	}
	public CompanyDto(String name, String type,List<JobDto> jobs) {
		super();
		this.name = name;
		this.type = type;
		this.jobs = jobs;
	}
	
	
	
    
    
}
