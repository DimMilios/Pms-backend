package com.pms.controller;

import com.pms.dao.InpatientDao;
import com.pms.model.patient.Inpatient;
import com.pms.model.patient.Patient;
import com.pms.model.staff.Staff;
import com.pms.service.InpatientService;
import com.pms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;


/**
 * The type Inpatient controller.
 */
@RestController
@RequestMapping(path = "api/inpatients")
@CrossOrigin
public class InpatientController {

    private InpatientDao inpatientDao;
    private InpatientService inpatientService;
    private final PatientService patientService;

    /**
     * Instantiates a new Inpatient controller.
     *
     * @param patientDao       the patient dao
     * @param inpatientService the inpatient service
     * @param patientService   the patient service
     */
    @Autowired
    public InpatientController(InpatientDao patientDao, InpatientService inpatientService,
                               PatientService patientService) {
        this.inpatientDao = patientDao;
        this.inpatientService = inpatientService;
        this.patientService = patientService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Iterable<Inpatient> getAll() {
        return inpatientDao.findAll();
    }

    /**
     * Gets by ssn.
     *
     * @param ssn the ssn
     * @return the by ssn
     */
    @GetMapping(path = "{ssn}")
    public Patient getBySsn(@PathVariable Long ssn) {
        return inpatientDao.findBySsn(ssn)
                .orElseThrow(() -> new RuntimeException("Could not find patient with ssn: " + ssn));
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
                        HttpStatus.NOT_FOUND, "Staff not found"));
    }

    /**
     * Create inpatient.
     *
     * @param patient the patient
     * @return the inpatient
     */
    @PostMapping
    public Inpatient create(@Valid @RequestBody Inpatient patient) {
        return inpatientDao.save(patient);
    }

    /**
     * Update inpatient.
     *
     * @param ssn        the ssn
     * @param newPatient the new patient
     * @return the inpatient
     */
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

    /**
     * Delete response entity.
     *
     * @param ssn the ssn
     * @return the response entity
     */
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
