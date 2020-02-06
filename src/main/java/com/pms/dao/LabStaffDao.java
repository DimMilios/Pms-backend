package com.pms.dao;

import com.pms.model.staff.LabStaff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Lab staff dao.
 */
@Repository
public interface LabStaffDao extends CrudRepository<LabStaff, Long> {

    /**
     * Find by user profile username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<LabStaff> findByUserProfileUsername(String username);
}
