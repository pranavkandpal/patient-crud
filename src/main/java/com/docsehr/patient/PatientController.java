package com.docsehr.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepo;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            return ResponseEntity.ok(patient);
        } else {
            throw new PatientNotFoundException("Patient with ID " + id + " not found");
        }
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        Patient savedPatient = patientRepo.save(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @Valid @RequestBody Patient updatedPatient) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setName(updatedPatient.getName());
            patient.setAge(updatedPatient.getAge());
            patient.setGender(updatedPatient.getGender());
            patient.setMobile(updatedPatient.getMobile());
            patient.setAddress(updatedPatient.getAddress());

            Patient savedPatient = patientRepo.save(patient);
            return ResponseEntity.ok(savedPatient);
        } else {
            throw new PatientNotFoundException("Patient with ID " + id + " not found");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        if (patientRepo.existsById(id)) {
            patientRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}