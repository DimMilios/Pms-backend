package com.pms.dao;

import com.pms.model.staff.PaymentStaff;
import org.springframework.data.repository.CrudRepository;

public interface PaymentStaffDao extends CrudRepository<PaymentStaff, Long> {
}
