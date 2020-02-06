package com.pms.dao;

import com.pms.model.patient.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Patient dao.
 */
@Repository
public interface PatientDao extends CrudRepository<Patient, Long> {

    /**
     * Find by ssn optional.
     *
     * @param ssn the ssn
     * @return the optional
     */
    Optional<Patient> findBySsn(Long ssn);

    /**
     * Find by user profile username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Patient> findByUserProfileUsername(String username);
}
