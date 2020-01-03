package com.pms.controller;

import com.pms.dao.DoctorDao;
import com.pms.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
