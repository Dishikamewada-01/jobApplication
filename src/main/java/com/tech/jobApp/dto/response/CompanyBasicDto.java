package com.tech.jobApp.dto.response;

public class CompanyBasicDto {

	 private String name;
	    private String type;

	    public CompanyBasicDto() {}

	    public CompanyBasicDto(String name, String type) {
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
