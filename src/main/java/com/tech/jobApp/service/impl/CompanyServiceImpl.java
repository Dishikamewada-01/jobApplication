package com.tech.jobApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.jobApp.model.Company;
import com.tech.jobApp.repository.CompanyRepository;
import com.tech.jobApp.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Long id, Company updatedCompany) {
        Company existing = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
        existing.setName(updatedCompany.getName());
        existing.setType(updatedCompany.getType());
        return companyRepository.save(existing);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> searchCompanyByName(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public List<Company> searchCompaniesByType(String type) {
        return companyRepository.findByType(type);
    }

	@Override
	public Optional<Company> getCompanyById(Long id) {
		return companyRepository.findById(id);
	}

}
