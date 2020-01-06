package com.pms.service;

import com.pms.dao.InpatientDao;
import com.pms.dao.PatientDao;
import com.pms.model.patient.Inpatient;
import com.pms.model.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InpatientService implements GenericService<Inpatient>{

    private InpatientDao patientDao;

    @Autowired
    public InpatientService(InpatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public  Iterable<Inpatient> getAll() {
        return null;
    }

    @Override
    public Optional<Inpatient> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Inpatient> create(Inpatient body) {
        return Optional.empty();
    }

    @Override
    public Optional<Inpatient> update(Inpatient body, Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
