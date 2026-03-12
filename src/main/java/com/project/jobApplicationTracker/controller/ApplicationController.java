package com.project.jobApplicationTracker.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.project.jobApplicationTracker.dto.CreateJobApplicationDTO;
import com.project.jobApplicationTracker.dto.JobApplicationDTO;
import com.project.jobApplicationTracker.model.JobApplication;
import com.project.jobApplicationTracker.model.User;
import com.project.jobApplicationTracker.service.ApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ApplicationController {

	private final ApplicationService applicationService;

	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@GetMapping("/applications")
	public ResponseEntity<List<JobApplicationDTO>> getAllApplications() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = applicationService.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Utente non trovato"));

		return ResponseEntity.ok(
				applicationService.trovaTutteJobApplicationsByUser(user).stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList())
				);
	}

	@GetMapping("/applications/{id}")
	public ResponseEntity<JobApplicationDTO> getApplicationById(@PathVariable Integer id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = applicationService.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Utente non trovato"));

		Optional<JobApplication> jobOpt = applicationService.trovaJobApplication(id);
		if (jobOpt.isEmpty() || !jobOpt.get().getUser().getId().equals(user.getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.ok(convertToDTO(jobOpt.get()));
	}

	@PostMapping("/applications")
	public ResponseEntity<JobApplicationDTO> createApplication(@Valid @RequestBody CreateJobApplicationDTO request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = applicationService.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Utente non trovato"));

		JobApplication job = new JobApplication();
		job.setAzienda(request.getAzienda());
		job.setPosizione(request.getPosizione());
		job.setStato(request.getStato());
		job.setDataCandidatura(request.getDataCandidatura());
		job.setNote(request.getNote());
		job.setUser(user);

		JobApplication savedJob = applicationService.salvaJobApplication(job);
		return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedJob));
	}

	@PutMapping("/applications/{id}")
	public ResponseEntity<JobApplicationDTO> updateApplication(
			@PathVariable Integer id, @Valid @RequestBody CreateJobApplicationDTO request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = applicationService.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Utente non trovato"));

		Optional<JobApplication> jobOpt = applicationService.trovaJobApplication(id);
		if (jobOpt.isEmpty() || !jobOpt.get().getUser().getId().equals(user.getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		JobApplication job = jobOpt.get();
		job.setAzienda(request.getAzienda());
		job.setPosizione(request.getPosizione());
		job.setStato(request.getStato());
		job.setDataCandidatura(request.getDataCandidatura());
		job.setNote(request.getNote());

		JobApplication updatedJob = applicationService.salvaJobApplication(job);
		return ResponseEntity.ok(convertToDTO(updatedJob));
	}

	@DeleteMapping("/applications/{id}")
	public ResponseEntity<Void> deleteApplication(@PathVariable Integer id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = applicationService.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Utente non trovato"));

		Optional<JobApplication> jobOpt = applicationService.trovaJobApplication(id);
		if (jobOpt.isEmpty() || !jobOpt.get().getUser().getId().equals(user.getId())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		applicationService.eliminaJobApplication(id);
		return ResponseEntity.noContent().build();
	}

	private JobApplicationDTO convertToDTO(JobApplication job) {
		return new JobApplicationDTO(
				job.getId(),
				job.getAzienda(),
				job.getPosizione(),
				job.getStato(),
				job.getDataCandidatura(),
				job.getNote(),
				job.getUser() != null ? job.getUser().getId() : null
				);
	}
}