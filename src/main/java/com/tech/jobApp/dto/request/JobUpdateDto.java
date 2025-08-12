package com.tech.jobApp.dto.request;

public class JobUpdateDto {

    private String title;
    private String description;
    private String location;
    private Double minSalary;
    private Double maxSalary;
    private Long companyId; // Ony Update Company Id

    // Getters and Setters
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

    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}

