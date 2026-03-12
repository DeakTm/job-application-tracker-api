package com.project.jobApplicationTracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {
	 @NotBlank(message = "Username obbligatorio")
	 @Size(min = 3, max = 50, message = "Username deve essere tra 3 e 50 caratteri")
    private String username;
	 @NotBlank(message = "Password obbligatoria")
	 @Size(min = 6, message = "Password deve essere almeno 6 caratteri")
    private String password;

    public RegisterRequestDTO() {
    	
    }
    
    public RegisterRequestDTO(String username, String password) {
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