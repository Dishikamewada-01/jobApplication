package com.tech.jobApp.dto.response;

import java.time.LocalDate;

public class JobDto {

	private String title;
    private String description;
    private String location;
    private double minSalary;
    private double maxSalary;
    private LocalDate postedAt;
    private CompanyDto company;
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
	public double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}
	public double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}
	public LocalDate getPostedAt() {
		return postedAt;
	}
	public void setPostedAt(LocalDate postedAt) {
		this.postedAt = postedAt;
	}
	public CompanyDto getCompany() {
		return company;
	}
	public void setCompany(CompanyDto company) {
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
