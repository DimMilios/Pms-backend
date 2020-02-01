package com.pms.service;

import com.pms.dao.DoctorDao;
import com.pms.model.staff.Doctor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DoctorService implements GenericService<Doctor> {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorService(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public Iterable<Doctor> getAll() {
        return null;
    }

    @Override
    public Optional<Doctor> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Doctor> create(Doctor body) {
        return Optional.empty();
    }

    @Override
    public Optional<Doctor> update(Doctor body, Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
