package com.health.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.patient.model.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
	

}
