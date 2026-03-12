package com.project.jobApplicationTracker.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
	@NotBlank(message = "Username obbligatorio")
    private String username;
	@NotBlank(message = "Username obbligatorio")
    private String password;

    public LoginRequestDTO() {}
    public LoginRequestDTO(String username, String password) { 
    	this.username = username; this.password = password; 
    	}

    public String getUsername() { return username; }
    public void setUsername(String username) { 
    	this.username = username; 
    	}

    public String getPassword() { return password; }
    public void setPassword(String password) { 
    	this.password = password; 
    	}
}
