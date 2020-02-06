package com.pms.dao;

import com.pms.model.patient.Patient;
import com.pms.model.staff.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Staff dao.
 */
@Repository
public interface StaffDao extends CrudRepository<Staff, Long> {

    /**
     * Find by user profile username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Staff> findByUserProfileUsername(String username);
}
