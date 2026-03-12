package com.project.jobApplicationTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.jobApplicationTracker.model.JobApplication;
import com.project.jobApplicationTracker.model.User;
import com.project.jobApplicationTracker.repository.JobApplicationRepository;
import com.project.jobApplicationTracker.repository.UserRepository;

@Service
public class ApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ApplicationService(JobApplicationRepository jobApplicationRepository, UserRepository userRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
    }

    //user

    public User registraUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
    
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    //jobApplication

    public List<JobApplication> trovaTutteJobApplicationsByUser(User user) {
        return jobApplicationRepository.findByUser(user);
    }
    
    public List<JobApplication> trovaTutteJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public Optional<JobApplication> trovaJobApplication(Integer id) {
        return jobApplicationRepository.findById(id);
    }

    public JobApplication salvaJobApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    public void eliminaJobApplication(Integer id) {
        jobApplicationRepository.deleteById(id);
    }
}