package com.health.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.models.Patient;
import com.health.patient.repository.PatientRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="Patient", description="l'API de Patient")
public class PatientController {
	
	@Autowired
	PatientRepository pRep;
	
	@Operation(summary="Ajouter un  patient", description="Ajouter un patient dans la base donnée")
	@PostMapping("/patient/add")
	public void insererPatient(@RequestBody Patient p) {
		pRep.save(p);
		
		
	}
	
	@Operation(summary="Lister tous les patients", description="Lister tous les patients ainsi que leurs données")
	@GetMapping("/patient")
	public List<Patient> listePatients(){
		
		return pRep.findAll();
	}
	
	@Operation(summary="Rechercher un patient", description="Rechercher un patient par cin ")
	@GetMapping("/patient/searchByCin/{cin}")
	public Optional<Patient> recherchePatientParId(@PathVariable int cin){
		
		return pRep.findById(cin);
	}
	@Operation(summary="Rechercher un patient", description="Rechercher un patient par sexe")
	@GetMapping("/patient/searchBySexe/{sexe}")
	public List<Patient> recherchePatientParSexe(@PathVariable String sexe){
		
		return pRep.findBySexe(sexe);
	}
	
	@Operation(summary="Rechercher un patient par tranche d'age", description="Rechercher un patient par tranche d'age")
	@GetMapping("/patient/searchByAgeRange/{min}/{max}")
	public List<Patient> recherchePatientParTrancheDage(@PathVariable int min,@PathVariable int max){
		
		return pRep.findByAgeBetween(min,max);
	}
	
	
	
	
	@Operation(summary="Supprimer un patient", description="Supprimer un patient par cin")
	@DeleteMapping("/patient/delete/{cin}")
		public void supprimerPatient(@PathVariable int cin) {
			
			pRep.deleteById(cin);
		
	}
	
	
	@Operation(summary="Supprimer tous les patients", description="Supprimer tous les patients")
	@DeleteMapping("/patient/delete")
	public void supprimerTousPatients() {
		pRep.deleteAll();
	}
	
	
	@Operation(summary="Modifier un patient", description="Modifier les parametres d'un patient")
	@PutMapping("/patient/modify/{cin}")
	public void modifierPatient(@PathVariable int cin,@RequestBody Patient modifiedPatient) {
		Optional<Patient> patient=pRep.findById(cin);
		if (patient.isPresent()) {
			Patient toModifyPatient=patient.get();
			toModifyPatient.setCin(modifiedPatient.getCin());
			toModifyPatient.setNom(modifiedPatient.getNom());
			toModifyPatient.setAge(modifiedPatient.getAge());
			toModifyPatient.setSexe(modifiedPatient.getSexe());
			pRep.save(toModifyPatient);
			
			
			
		}
		
		
	}
	

}

