package com.pms.controller;

import com.pms.dao.PatientDao;
import com.pms.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/patients")
@CrossOrigin
public class PatientController {

    private PatientDao patientDao;

    @Autowired
    public PatientController(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @GetMapping
    public Iterable<Patient> getAll() {
        return patientDao.findAll();
    }

    @GetMapping(path = "{ssn}")
    public Patient getBySsn(@PathVariable Long ssn) {
        return patientDao.findBySsn(ssn)
                .orElseThrow(() -> new RuntimeException("Could not find patient with ssn: " + ssn));
    }

    @PostMapping
    public Patient create(@Valid @RequestBody Patient patient) {
        return patientDao.save(patient);
    }

    @PutMapping(path = "{ssn}")
    public Patient update(@PathVariable Long ssn, @Valid @RequestBody Patient newPatient) {
        Optional<Patient> patientToChange = patientDao.findBySsn(ssn);
        return patientToChange.map(patient -> {
            patient.setUserProfile(newPatient.getUserProfile());
            patient.setFullName(newPatient.getFullName());
            patient.setSex(newPatient.getSex());
            patient.setOccupation(newPatient.getOccupation());
            return patientDao.save(patient);
        })
                .orElseThrow(() -> new RuntimeException("Could not update patient with ssn: " + ssn));
    }

    @DeleteMapping(path = "{ssn}")
    public ResponseEntity<?> delete(@PathVariable Long ssn) {
        return patientDao.findBySsn(ssn)
                .map(patient -> {
                    patientDao.delete(patient);
                    return ResponseEntity.ok()
                            .body("Patient with ssn: " + ssn + " deleted");
                })
                .orElseThrow(() -> new RuntimeException("Could not find patient with ssn: " + ssn));
    }
}
