package com.tech.jobApp.service;

import java.util.List;
import java.util.Optional;

import com.tech.jobApp.model.Company;

//Interface for abstraction
public interface CompanyService {
    
    Company createCompany(Company company);
    
    Company updateCompany(Long id, Company updatedCompany);
    
    void deleteCompany(Long id);
    
    List<Company> getAllCompanies();
    
    Optional<Company> searchCompanyByName(String name);
    
    List<Company> searchCompaniesByType(String type);

	Optional<Company> getCompanyById(Long id);
}
