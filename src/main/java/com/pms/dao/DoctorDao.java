package com.pms.dao;

import com.pms.model.staff.Doctor;
import com.pms.model.staff.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Doctor dao.
 */
@Repository
public interface DoctorDao extends CrudRepository<Doctor, Long> {

    /**
     * Find by user profile username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Doctor> findByUserProfileUsername(String username);
}
