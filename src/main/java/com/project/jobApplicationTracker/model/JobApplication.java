package com.project.jobApplicationTracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jobApplication")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String azienda;

    private String posizione;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus stato;

    private LocalDate dataCandidatura;

    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    //costruttori
    public JobApplication() {
    }

    public JobApplication(Integer id, String azienda, String posizione,
    		ApplicationStatus stato, LocalDate dataCandidatura,
                       String note, User user) {
        this.id = id;
        this.azienda = azienda;
        this.posizione = posizione;
        this.stato = stato;
        this.dataCandidatura = dataCandidatura;
        this.note = note;
        this.user = user;
    }

    
    //get/setters
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
}