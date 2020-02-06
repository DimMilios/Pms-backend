package com.pms.dao;

import com.pms.model.patient.Patient;
import com.pms.model.staff.PaymentStaff;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The interface Payment staff dao.
 */
public interface PaymentStaffDao extends CrudRepository<PaymentStaff, Long> {

    /**
     * Find by user profile username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<PaymentStaff> findByUserProfileUsername(String username);
}
