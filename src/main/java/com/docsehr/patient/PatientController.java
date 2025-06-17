package com.docsehr.patient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private List<Patient> patients = new ArrayList<>();
    private int idCounter = 1;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patients;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
        for (Patient p : patients) {
            if (Integer.parseInt(p.getId()) == id) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        patient.setId(String.valueOf(idCounter++));
        patients.add(patient);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient updatedPatient) {
        for (Patient p : patients) {
            if (Integer.parseInt(p.getId()) == id) {
                p.setName(updatedPatient.getName());
                p.setAge(updatedPatient.getAge());
                p.setGender(updatedPatient.getGender());
                p.setMobile(updatedPatient.getMobile());
                p.setAddress(updatedPatient.getAddress());
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        for (Patient p : patients) {
            if (Integer.parseInt(p.getId()) == id) {
                patients.remove(p);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
