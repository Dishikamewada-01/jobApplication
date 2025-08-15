package com.tech.jobApp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.jobApp.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
	
	    Page<Job> findByTitleContainingIgnoreCase(String title , Pageable pageable );
	    Page<Job> findByLocationContainingIgnoreCase(String location , Pageable pageable );
	    Page<Job> findByCompany_NameIgnoreCase(String companyName , Pageable pageable);

}
