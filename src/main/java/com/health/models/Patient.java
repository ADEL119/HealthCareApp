package com.health.models;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "patient")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"dateDebutTraitement"}, allowGetters =true)
public class Patient {
	@Id
	private int cin;
	@NotBlank(message = "La valeur nom ne peut pas être vide")
	private String nom;
	@NotBlank(message = "La valeur sexe ne peut pas être vide")
	private String sexe;
	private int age;
	
	
	@Column(nullable=false, updatable=false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date dateDebutTraitement;
	public Patient(int cin, @NotBlank(message = "La valeur nom ne peut pas être vide") String nom,
			@NotBlank(message = "La valeur sexe ne peut pas être vide") String sexe, int age,
			Date dateDebutTraitement) {
		this.cin = cin;
		this.nom = nom;
		this.sexe = sexe;
		this.age = age;
		this.dateDebutTraitement = dateDebutTraitement;
	}
	@PrePersist
    public void prePersist() {
        if (this.dateDebutTraitement == null) {
            this.dateDebutTraitement = new Date();  // Utilise la date actuelle si aucune date n'est fournie
        }
    }
	public Patient() {
		
	}
	
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Date getDateDebutTraitement() {
		return dateDebutTraitement;
	}
	public void setDateDebutTraitement(Date dateDebutTraitement) {
		this.dateDebutTraitement = dateDebutTraitement;
	} 
	
	
	
	

	
	

}
