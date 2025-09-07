package com.tech.jobApp.dto.request;

import jakarta.validation.constraints.NotNull;


public class CompanyCreateDto {

	@NotNull
	private String name;
	
	@NotNull
    private String type;

    public CompanyCreateDto() {}

    public CompanyCreateDto(String name, String type) {
        this.name = name;
        this.type = type;
    }

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
	
}
