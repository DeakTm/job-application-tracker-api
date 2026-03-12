package com.project.jobApplicationTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jobApplicationTracker.model.JobApplication;
import com.project.jobApplicationTracker.model.User;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer>{
	 List<JobApplication> findByUser(User user);
}
