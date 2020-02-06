package com.pms.dao;

import com.pms.model.patient.Patient;
import com.pms.model.staff.Receptionist;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The interface Receptionist dao.
 */
public interface ReceptionistDao extends CrudRepository<Receptionist, Long> {

    /**
     * Find by user profile username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Receptionist> findByUserProfileUsername(String username);
}
