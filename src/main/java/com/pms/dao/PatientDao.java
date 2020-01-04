package com.pms.dao;

import com.pms.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientDao extends CrudRepository<Patient, Long> {

    Optional<Patient> findBySsn(Long ssn);
}
