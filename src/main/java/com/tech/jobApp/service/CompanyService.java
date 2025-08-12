package com.tech.jobApp.service;

import java.util.List;
import java.util.Optional;

import com.tech.jobApp.dto.request.CompanyUpdateDto;
import com.tech.jobApp.dto.response.CompanyDto;
import com.tech.jobApp.model.Company;

//Interface for abstraction
public interface CompanyService {
    
	// Create or Post new Company
    Company createCompany(Company company);
    
    // Update an existing Company
    void updateCompany(Long id, CompanyUpdateDto updatedCompanyDto);
    
    // Delete an Existing Company
    void deleteCompany(Long id);
    
    // Get all companies
    List<CompanyDto> getAllCompanies();
    
    // Search Company By name
     CompanyDto searchCompanyByName(String name);
    
    // Search Company By Type
    List<CompanyDto> searchCompaniesByType(String type);

    
    // Get Company by company id
	CompanyDto getCompanyById(Long id);
}
