package com.pms.controller;

import com.pms.dao.DoctorDao;
import com.pms.model.staff.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/doctors")
@CrossOrigin
public class DoctorController {

    private DoctorDao doctorDao;

    @Autowired
    public DoctorController(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @GetMapping
    public Iterable<Doctor> getAll() {
        return doctorDao.findAll();
    }

    @GetMapping(path = "{doctorId}")
    public Doctor getById(@PathVariable("doctorId") Long doctorId) {
        return doctorDao.findById(doctorId)
                .orElseThrow(() ->
                        new RuntimeException("Could not find doctor with id: " + doctorId));
    }

    @PostMapping
    public Doctor createDoctor (@Valid @RequestBody Doctor doctorToAdd) {
        return doctorDao.save(doctorToAdd);
    }

    @PutMapping(path = "{doctorId}")
    public Doctor updateDoctor(
            @Valid @RequestBody Doctor doctorToUpdate,
            @PathVariable Long doctorId) {
        Optional<Doctor> docById = doctorDao.findById(doctorId);
        Optional<Doctor> doctor1 = docById.map(doctor -> {
            doctor.setUserProfile(doctorToUpdate.getUserProfile());
            doctor.setAddress(doctorToUpdate.getAddress());
            doctor.setFullName(doctorToUpdate.getFullName());
            doctor.setPhoneNumbers(doctorToUpdate.getPhoneNumbers());
            return doctor;
        });

        return doctorDao.save(doctor1.get());
    }

    @DeleteMapping(path = "{doctorId}")
    public ResponseEntity<?> delete(@PathVariable("doctorId") Long doctorId) {
        doctorDao.deleteById(doctorId);
        return ResponseEntity.ok()
                .body("Doctor with id: " + doctorId + " deleted");
    }
}
