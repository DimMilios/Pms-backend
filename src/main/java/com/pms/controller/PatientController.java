package com.pms.controller;

import com.pms.dao.PatientDao;
import com.pms.model.patient.Patient;
import com.pms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/patients")
@CrossOrigin
public class PatientController {

    private PatientDao patientDao;
    private PatientService patientService;

    @Autowired
    public PatientController(PatientDao patientDao, PatientService patientService) {
        this.patientDao = patientDao;
        this.patientService = patientService;
    }

    @GetMapping
    public Iterable<Patient> getAll() {
        return patientService.getAll();
    }

    @GetMapping(path = "{ssn}")
    public Patient getBySsn(@PathVariable Long ssn) {
        return patientService.getById(ssn)
                .orElseThrow(() -> new RuntimeException("Could not find patient with ssn: " + ssn));
    }

    @PostMapping
    public Patient create(@Valid @RequestBody Patient patient) {
        return patientService.create(patient)
                .orElseThrow(() -> new RuntimeException("Error creating patient"));
    }

    @PutMapping(path = "{ssn}")
    public Patient update(@PathVariable Long ssn, @Valid @RequestBody Patient newPatient) {
        return patientService.update(newPatient, ssn)
                .orElseThrow(() -> new RuntimeException("Could not update patient with ssn: " + ssn));
    }

    @DeleteMapping(path = "{ssn}")
    public ResponseEntity<?> delete(@PathVariable Long ssn) {
        try {
            patientService.delete(ssn);
            return ResponseEntity.ok()
                        .body("Patient with ssn: " + ssn + " deleted");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not find patient with ssn: " + ssn);
        }
    }
}
