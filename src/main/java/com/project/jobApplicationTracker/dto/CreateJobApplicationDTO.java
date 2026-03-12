package com.project.jobApplicationTracker.dto;

import java.time.LocalDate;
import com.project.jobApplicationTracker.model.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;

public class CreateJobApplicationDTO {

    @NotBlank
    private String azienda;

    @NotBlank
    private String posizione;

    private ApplicationStatus stato;
    private LocalDate dataCandidatura;
    private String note;

    public CreateJobApplicationDTO() {}

    public String getAzienda() { return azienda; }
    public void setAzienda(String azienda) { this.azienda = azienda; }

    public String getPosizione() { return posizione; }
    public void setPosizione(String posizione) { this.posizione = posizione; }

    public ApplicationStatus getStato() { return stato; }
    public void setStato(ApplicationStatus stato) { this.stato = stato; }

    public LocalDate getDataCandidatura() { return dataCandidatura; }
    public void setDataCandidatura(LocalDate dataCandidatura) { this.dataCandidatura = dataCandidatura; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

}