package com.project.jobApplicationTracker.dto;

import java.time.LocalDate;

import com.project.jobApplicationTracker.model.ApplicationStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobApplicationDTO {

	    private Integer id;
	    @NotBlank(message = "Azienda obbligatoria")
	    private String azienda;
	    @NotBlank(message = "Posizione obbligatoria")
	    private String posizione;
	    @NotNull(message = "Stato obbligatorio")
	    private ApplicationStatus stato;
	    @NotNull(message = "Data candidatura obbligatoria")
	    private LocalDate dataCandidatura;
	    private String note;
	    @NotNull(message = "UserId obbligatorio")
	    private Integer userId; 

	    public JobApplicationDTO() {
	    	
	    }
		public JobApplicationDTO(Integer id, String azienda, String posizione, ApplicationStatus stato,
				LocalDate dataCandidatura, String note, Integer userId) {
			super();
			this.id = id;
			this.azienda = azienda;
			this.posizione = posizione;
			this.stato = stato;
			this.dataCandidatura = dataCandidatura;
			this.note = note;
			this.userId = userId;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getAzienda() {
			return azienda;
		}

		public void setAzienda(String azienda) {
			this.azienda = azienda;
		}

		public String getPosizione() {
			return posizione;
		}

		public void setPosizione(String posizione) {
			this.posizione = posizione;
		}

		public ApplicationStatus getStato() {
			return stato;
		}

		public void setStato(ApplicationStatus stato) {
			this.stato = stato;
		}

		public LocalDate getDataCandidatura() {
			return dataCandidatura;
		}

		public void setDataCandidatura(LocalDate dataCandidatura) {
			this.dataCandidatura = dataCandidatura;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}
	    
}
