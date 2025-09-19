package com.tech.jobApp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.jobApp.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company ,Long> {
	 Optional<Company> findByName(String name);
	    Page<Company> findByType(String type , Pageable pageable);
}
