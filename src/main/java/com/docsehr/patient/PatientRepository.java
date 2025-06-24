package com.docsehr.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientRepository extends MongoRepository<Patient, String> {
    List<Patient> findByNameContainingIgnoreCase(String name);
    List<Patient> findByGenderIgnoreCase(String gender);
    List<Patient> findByNameContainingIgnoreCaseAndGenderIgnoreCase(String name, String gender);


    Page<Patient> findAll(Pageable pageable);
}
