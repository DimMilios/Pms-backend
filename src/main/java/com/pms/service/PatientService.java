package com.pms.service;

import com.pms.dao.PatientDao;
import com.pms.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PatientService {

    private PatientDao patientDao;

    @Autowired
    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> getAllPatients() {
        return StreamSupport.stream(
                patientDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteBySsn(Long ssn) {
        patientDao.deleteById(ssn);
    }
}
