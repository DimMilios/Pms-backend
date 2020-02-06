package com.pms.controller;

import com.pms.dao.PatientDao;
import com.pms.model.patient.Patient;
import com.pms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

/**
 * The type Patient controller.
 */
@RestController
@RequestMapping(path = "api/patients")
@CrossOrigin
public class PatientController {

    private PatientDao patientDao;
    private PatientService patientService;

    /**
     * Instantiates a new Patient controller.
     *
     * @param patientDao     the patient dao
     * @param patientService the patient service
     */
    @Autowired
    public PatientController(PatientDao patientDao, PatientService patientService) {
        this.patientDao = patientDao;
        this.patientService = patientService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Iterable<Patient> getAll() {
        return patientService.getAll();
    }

    /**
     * Gets by ssn.
     *
     * @param ssn the ssn
     * @return the by ssn
     */
    @GetMapping(path = "{ssn}")
    public Patient getBySsn(@PathVariable Long ssn) {
        return patientService.getById(ssn)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Could not find patient with ssn: " + ssn));
    }

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    @GetMapping(path = "username/{username}")
    public Patient getByUsername(@PathVariable String username) {
        return patientService.getByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Patient not found"));
    }

    /**
     * Create patient.
     *
     * @param patient the patient
     * @return the patient
     */
    @PostMapping
    public Patient create(@Valid @RequestBody Patient patient) {
        return patientService.create(patient)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error creating patient"));
    }

    /**
     * Update patient.
     *
     * @param ssn        the ssn
     * @param newPatient the new patient
     * @return the patient
     */
    @PutMapping(path = "{ssn}")
    public Patient update(@PathVariable Long ssn, @Valid @RequestBody Patient newPatient) {
        return patientService.update(newPatient, ssn)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error creating patient with ssn: " + ssn));
    }

    /**
     * Delete response entity.
     *
     * @param ssn the ssn
     * @return the response entity
     */
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
