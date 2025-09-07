package com.tech.jobApp.dto.response;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class JobDto {

	private String title;

	private String description;

	private String location;

	private Double minSalary;

	private Double maxSalary;
	
    private LocalDate postedAt;
    
    private CompanyBasicDto company;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}
	public Double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}
	public LocalDate getPostedAt() {
		return postedAt;
	}
	public void setPostedAt(LocalDate postedAt) {
		this.postedAt = postedAt;
	}
	public CompanyBasicDto getCompany() {
		return company;
	}
	public void setCompany(CompanyBasicDto company) {
		this.company = company;
	}
	public JobDto() {
		
	}
	@Override
	public String toString() {
		return "JobDto [title=" + title + ", description=" + description + ", location=" + location + ", minSalary="
				+ minSalary + ", maxSalary=" + maxSalary + ", postedAt=" + postedAt + ", company=" + company + "]";
	}
    
    
}
