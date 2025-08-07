package com.tech.jobApp.dto.response;

public class CompanyDto {

	private String name;
    private String type;
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
	public CompanyDto() {
	}
	public CompanyDto(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
    
    
}
