package com.project.jobApplicationTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.project.jobApplicationTracker.controller","com.project.jobApplicationTracker.filter","com.project.jobApplicationTracker.security","com.project.jobApplicationTracker.exception","com.project.jobApplicationTracker.service"})
public class JobApplicationTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobApplicationTrackerApplication.class, args);
	}

}
