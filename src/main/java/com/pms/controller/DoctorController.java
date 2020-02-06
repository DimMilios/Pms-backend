package com.pms.controller;

import com.pms.dao.DoctorDao;
import com.pms.model.staff.Doctor;
import com.pms.model.staff.Staff;
import com.pms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

/**
 * The type Doctor controller.
 */
@RestController
@RequestMapping("api/doctors")
@CrossOrigin
public class DoctorController {

    private DoctorDao doctorDao;
    private final DoctorService doctorService;

    /**
     * Instantiates a new Doctor controller.
     *
     * @param doctorDao the doctor dao
     * @param doctorService
     */
    @Autowired
    public DoctorController(DoctorDao doctorDao, DoctorService doctorService) {
        this.doctorDao = doctorDao;
        this.doctorService = doctorService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Iterable<Doctor> getAll() {
        return doctorService.getAll();
    }

    /**
     * Gets by id.
     *
     * @param doctorId the doctor id
     * @return the by id
     */
    @GetMapping(path = "{doctorId}")
    public Doctor getById(@PathVariable("doctorId") Long doctorId) {
        return doctorService.getById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Doctor not found"));
    }

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    @GetMapping(path = "username/{username}")
    public Staff getByUsername(@PathVariable String username) {
        return doctorService.getByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Doctor not found"));
    }

    /**
     * Create doctor doctor.
     *
     * @param doctorToAdd the doctor to add
     * @return the doctor
     */
    @PostMapping
    public Doctor createDoctor (@Valid @RequestBody Doctor doctorToAdd) {
        return doctorService.create(doctorToAdd)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error creating doctor"));
    }

    /**
     * Update doctor doctor.
     *
     * @param doctorToUpdate the doctor to update
     * @param doctorId       the doctor id
     * @return the doctor
     */
    @PutMapping(path = "{doctorId}")
    public Doctor updateDoctor(
            @Valid @RequestBody Doctor doctorToUpdate,
            @PathVariable Long doctorId) {
        return doctorService.update(doctorToUpdate, doctorId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error updating doctor"));
    }

    /**
     * Delete response entity.
     *
     * @param doctorId the doctor id
     * @return the response entity
     */
    @DeleteMapping(path = "{doctorId}")
    public ResponseEntity<?> delete(@PathVariable("doctorId") Long doctorId) {
        doctorService.delete(doctorId);
        return ResponseEntity.ok()
                .body("Doctor with id: " + doctorId + " deleted");
    }
}
