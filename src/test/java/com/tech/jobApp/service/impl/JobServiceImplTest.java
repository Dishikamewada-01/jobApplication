package com.tech.jobApp.service.impl;

import com.tech.jobApp.dto.request.JobCreateDto;
import com.tech.jobApp.dto.request.JobUpdateDto;
import com.tech.jobApp.dto.response.JobDto;
import com.tech.jobApp.model.Company;
import com.tech.jobApp.model.Job;
import com.tech.jobApp.repository.CompanyRepository;
import com.tech.jobApp.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private JobServiceImpl jobService; // testing real service with mocked repos

    private Company company;
    private Job job;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        company = new Company("TCS", "Service");
        company.setJobs(List.of());

        job = new Job("Java Dev", "Backend role", "Pune", 30000.0, 60000.0, company);
    }

    @Test
    void createJob_success() {
        JobCreateDto dto = new JobCreateDto();
        dto.setTitle("Java Dev");
        dto.setDescription("Backend role");
        dto.setLocation("Pune");
        dto.setMinSalary(30000.0);
        dto.setMaxSalary(60000.0);
        dto.setCompanyId(1L);

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        JobDto result = jobService.createJob(dto);

        assertThat(result.getTitle()).isEqualTo("Java Dev");
        assertThat(result.getCompany().getName()).isEqualTo("TCS");
        verify(jobRepository, times(1)).save(any(Job.class));
    }

    @Test
    void createJob_companyNotFound_throwsException() {
        JobCreateDto dto = new JobCreateDto();
        dto.setCompanyId(99L);

        when(companyRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> jobService.createJob(dto));
    }

    @Test
    void updateJob_success() {
        JobUpdateDto dto = new JobUpdateDto();
        dto.setTitle("Updated Title");

        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        JobDto result = jobService.updateJob(1L, dto);

        assertThat(result.getTitle()).isEqualTo("Updated Title");
        verify(jobRepository).save(any(Job.class));
    }

    @Test
    void deleteJob_success() {
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        jobService.deleteJob(1L);

        verify(jobRepository).delete(job);
    }

    @Test
    void getAllJobs_success() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Job> jobPage = new PageImpl<>(List.of(job), pageable, 1);

        when(jobRepository.findAll(pageable)).thenReturn(jobPage);

        List<JobDto> results = jobService.getAllJobs(0, 2);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getLocation()).isEqualTo("Pune");
    }

    @Test
    void getJobById_found() {
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        JobDto result = jobService.getJobById(1L);

        assertThat(result.getTitle()).isEqualTo("Java Dev");
    }

    @Test
    void searchJobsByTitle_success() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<Job> jobPage = new PageImpl<>(List.of(job), pageable, 1);

        when(jobRepository.findByTitleContainingIgnoreCase("Java", pageable)).thenReturn(jobPage);

        List<JobDto> results = jobService.searchJobsByTitle("Java", 0, 1);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).contains("Java");
    }
}
