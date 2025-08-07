package com.tech.jobApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.jobApp.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
	
	    List<Job> findByTitleContainingIgnoreCase(String title);
	    List<Job> findByLocationContainingIgnoreCase(String location);
	    List<Job> findByCompany_NameIgnoreCase(String companyName);

}
