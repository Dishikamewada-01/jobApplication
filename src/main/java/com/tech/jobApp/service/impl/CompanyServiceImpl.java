package com.tech.jobApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.jobApp.dto.request.CompanyUpdateDto;
import com.tech.jobApp.dto.response.CompanyDto;
import com.tech.jobApp.mapper.CompanyMapper;
import com.tech.jobApp.mapper.JobMapper;
import com.tech.jobApp.model.Company;
import com.tech.jobApp.model.Job;
import com.tech.jobApp.repository.CompanyRepository;
import com.tech.jobApp.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
    private CompanyRepository companyRepository;

	// Create or Post Company
    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    
    // Update an Existing Company
    @Override
    public CompanyDto updateCompany(Long id, CompanyUpdateDto updatedCompanyDto) {
        Company existing = companyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Company not found"));

     // Sirf non-null fields ko update karo
        if (updatedCompanyDto.getName() != null) {
            existing.setName(updatedCompanyDto.getName());
        }
        if (updatedCompanyDto.getType() != null) {
            existing.setType(updatedCompanyDto.getType());
        }
        

        Company updatedCompany = companyRepository.save(existing);
        return CompanyMapper.mapCompanyToDto(updatedCompany);
 
    }

    
    // Delete a Company
    @Override
    public void deleteCompany(Long id) {
    	Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + id));
        companyRepository.delete(company);
    }

    
    // Get All Companies
    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDto> companyDtos = new ArrayList<>();
        
        for(Company company : companies) {
        	CompanyDto dto = CompanyMapper.mapCompanyToDto(company);
        	companyDtos.add(dto);
        }
        
        return companyDtos;
    }

    
    // Search Company By name
    @Override
    public CompanyDto searchCompanyByName(String name) {
    	Optional<Company> companyOpt = companyRepository.findByName(name);
        if (companyOpt!=null) {
            return CompanyMapper.mapCompanyToDto(companyOpt.get()); // Only name + type
        }
        return null; // or throw exception
    }
    

    
    // Search Company by Type
    @Override
    public List<CompanyDto> searchCompaniesByType(String type) {
        List<Company> companies= companyRepository.findByType(type);
        List<CompanyDto> comDtos= new ArrayList<>();
        for(Company company : companies) {
        	comDtos.add(CompanyMapper.mapCompanyToDto(company));
        }
        
        return comDtos;
    }

    
    // Search Company By Id
	@Override
	public CompanyDto getCompanyById(Long id) {
		Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            return CompanyMapper.mapCompanyToDto(company);
        }
        return null;
	}

}
