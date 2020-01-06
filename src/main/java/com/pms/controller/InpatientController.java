package com.pms.controller;

import com.pms.dao.InpatientDao;
import com.pms.model.patient.Inpatient;
import com.pms.model.patient.Patient;
import com.pms.service.InpatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/inpatients")
@CrossOrigin
public class InpatientController {

    private InpatientDao inpatientDao;
    private InpatientService inpatientService;

    @Autowired
    public InpatientController(InpatientDao patientDao, InpatientService inpatientService) {
        this.inpatientDao = patientDao;
        this.inpatientService = inpatientService;
    }

    @GetMapping
    public Iterable<Inpatient> getAll() {
        return inpatientDao.findAll();
    }

    @GetMapping(path = "{ssn}")
    public Patient getBySsn(@PathVariable Long ssn) {
        return inpatientDao.findBySsn(ssn)
                .orElseThrow(() -> new RuntimeException("Could not find patient with ssn: " + ssn));
    }

    @PostMapping
    public Inpatient create(@Valid @RequestBody Inpatient patient) {
        return inpatientDao.save(patient);
    }

    @PutMapping(path = "{ssn}")
    public Inpatient update(@PathVariable Long ssn, @Valid @RequestBody Inpatient newPatient) {
        Optional<Inpatient> patientToChange = inpatientDao.findBySsn(ssn);
        return patientToChange.map(patient -> {
            patient.setUserProfile(newPatient.getUserProfile());
            patient.setFullName(newPatient.getFullName());
            patient.setSex(newPatient.getSex());
            patient.setOccupation(newPatient.getOccupation());
            patient.setAdmitDate(newPatient.getAdmitDate());
            patient.setDischargeDate(newPatient.getDischargeDate());
            patient.setCurrentDiagnosis(newPatient.getCurrentDiagnosis());
            return inpatientDao.save(patient);
        })
                .orElseThrow(() -> new RuntimeException("Could not update patient with ssn: " + ssn));
    }

    @DeleteMapping(path = "{ssn}")
    public ResponseEntity<?> delete(@PathVariable Long ssn) {
        return inpatientDao.findBySsn(ssn)
                .map(patient -> {
                    inpatientDao.delete(patient);
                    return ResponseEntity.ok()
                            .body("Patient with ssn: " + ssn + " deleted");
                })
                .orElseThrow(() -> new RuntimeException("Could not find patient with ssn: " + ssn));
    }
}
