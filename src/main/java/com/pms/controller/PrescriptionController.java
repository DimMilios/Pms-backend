package com.pms.controller;

import com.pms.model.prescription.Prescription;
import com.pms.service.DoctorService;
import com.pms.service.PatientService;
import com.pms.service.PrescriptionService;
import com.pms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;

/**
 * The type Prescription controller.
 */
@RestController
@RequestMapping("api/prescriptions")
@CrossOrigin
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    /**
     * Instantiates a new Prescription controller.
     *
     * @param prescriptionService the prescription service
     */
    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Iterable<Prescription> getAll() {
        return prescriptionService.getAll();
    }

    /**
     * Create prescription.
     *
     * @param date         the date
     * @param prescription the prescription
     * @return the prescription
     */
    @PostMapping
    public Prescription create(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date,
                               @RequestBody @Valid Prescription prescription) {

        return prescriptionService.create(prescription, date)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error creating prescription!"
                ));
    }


    /**
     * Delete by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        prescriptionService.deleteById(id);

        return ResponseEntity.ok().body("Prescription with id: " + id + " deleted!");
    }

    /**
     * Delete by patient response entity.
     *
     * @param ssn  the ssn
     * @param date the date
     * @return the response entity
     */
    @DeleteMapping(path = "patient")
    public ResponseEntity<?> deleteByPatient(@RequestParam Long ssn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        if (!prescriptionService.getByPatient(ssn).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found!");
        }

        if (!prescriptionService.getByDate(date).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Date not found!");
        }

        prescriptionService.deleteByPatientAndDate(ssn, date);

        return ResponseEntity.ok().body("Prescription deleted!");
    }

    /**
     * Delete by doctor response entity.
     *
     * @param doctorId the doctor id
     * @param date     the date
     * @return the response entity
     */
    @DeleteMapping(path = "doctor")
    public ResponseEntity<?> deleteByDoctor(@RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        if (!prescriptionService.getByDoctor(doctorId).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found!");
        }

        if (!prescriptionService.getByDate(date).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Date not found!");
        }

        prescriptionService.deleteByDoctorAndDate(doctorId, date);

        return ResponseEntity.ok().body("Prescription deleted!");
    }
}
