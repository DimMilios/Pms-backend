package com.pms.service;

import com.pms.dao.PatientDao;
import com.pms.model.patient.Patient;
import com.pms.model.patient.PatientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PatientService implements GenericService<Patient> {

    private PatientDao patientDao;

    @Autowired
    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }


    @Override
    public Iterable<Patient> getAll() {
        return patientDao.findAll();
    }

    @Override
    public Optional<Patient> getById(Long id) {
        return patientDao.findBySsn(id);
    }

    @Override
    public Optional<Patient> create(Patient patient) {
        Patient patientToAdd = getBuild(patient, patient.getSsn());

        return Optional.of(patientDao.save(patientToAdd));
    }

    @Override
    public Optional<Patient> update(Patient patient, Long id) {
        Patient patientToAdd = getBuild(patient, id);

        return Optional.of(patientDao.save(patientToAdd));
    }

    @Override
    public void delete(Long id) throws EmptyResultDataAccessException {
//        try {
            patientDao.deleteById(id);
//        } catch (EmptyResultDataAccessException ex) {
//            throw new RuntimeException("Patient with this id does not exist");
//        }
    }

    private Patient getBuild(Patient patient, Long id) {
        return PatientBuilder
                .patient()
                .withSsn(id)
                .withFullName(patient.getFullName())
                .withOccupation(patient.getOccupation())
                .withUserProfile(patient.getUserProfile())
                .withSex(patient.getSex().toString())
//                .withAppointments(patient.getAppointments())
                .build();
    }
}
