package com.tech.jobApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.jobApp.dto.request.CompanyUpdateDto;
import com.tech.jobApp.dto.response.CompanyDto;
import com.tech.jobApp.model.Company;
import com.tech.jobApp.service.CompanyService;



@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    
    // Create or Post Company
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.createCompany(company));
    }

    
    //Update Company
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, @RequestBody CompanyUpdateDto updatedCompanyDto) {
       return ResponseEntity.ok(companyService.updateCompany(id, updatedCompanyDto));
    }
    
   

    
    // Delete Company
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok("Company deleted successfully");
    }

    
    // Get All Companies
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
    	List<CompanyDto> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
        
    }
    
    
    // Get Company By id
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
       CompanyDto companyDto = companyService.getCompanyById(id);
       return ResponseEntity.ok(companyDto);
    }

    
    // Search Company By name
    @GetMapping("/name/{name}")
    public ResponseEntity<CompanyDto> getCompanyByName(@PathVariable String name) {
    	CompanyDto company = companyService.searchCompanyByName(name);
        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }

    
    // Search Company by Type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<CompanyDto>> getCompaniesByType(@PathVariable String type) {
    	List<CompanyDto> companies = companyService.searchCompaniesByType(type);
        return ResponseEntity.ok(companies);
    }
}