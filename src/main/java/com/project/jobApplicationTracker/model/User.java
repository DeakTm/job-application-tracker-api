package com.project.jobApplicationTracker.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "utenti") 
public class User {
	
	   	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @NotBlank
	    @Column(unique = true, nullable = false)
	    private String username;

	    @NotBlank
	    private String password;
	    
	    @OneToMany(mappedBy = "user")
	    private List<JobApplication> candidature;
	    
	    // costruttori 
	    public User() {
	    }


	    public User(Integer id, String username, String password) {
	        this.id = id;
	        this.username = username;
	        this.password = password;
	    }

	    
	    
	    // get/setters
	    public Integer getId() { return id; }
	    public void setId(Integer id) {
	    	this.id = id; 
	    	}

	    public String getUsername() {
	    	return username; 
	    	}
	    public void setUsername(String username) { 
	    	this.username = username; 
	    	}

	    public String getPassword() { 
	    	return password; 
	    	}
	    public void setPassword(String password) {
	    	this.password = password;
	    	}
	}

