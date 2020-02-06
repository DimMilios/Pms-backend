package com.pms.dao;

import com.pms.model.patient.Inpatient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Inpatient dao.
 */
@Repository
public interface InpatientDao extends CrudRepository<Inpatient, Long> {

    /**
     * Find by ssn optional.
     *
     * @param ssn the ssn
     * @return the optional
     */
    Optional<Inpatient> findBySsn(Long ssn);

    /**
     * Find by user profile username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Inpatient> findByUserProfileUsername(String username);
}
