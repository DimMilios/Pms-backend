package com.pms.service;

import com.pms.dao.PaymentStaffDao;
import com.pms.model.staff.PaymentStaff;
import com.pms.model.staff.StaffMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentStaffService implements GenericService<PaymentStaff> {

    private final PaymentStaffDao paymentStaffDao;
    private final StaffMapper staffMapper;

    public PaymentStaffService(PaymentStaffDao paymentStaffDao,
                               StaffMapper staffMapper) {
        this.paymentStaffDao = paymentStaffDao;
        this.staffMapper = staffMapper;
    }

    @Override
    public Iterable<PaymentStaff> getAll() {
        return paymentStaffDao.findAll();
    }

    @Override
    public Optional<PaymentStaff> getById(Long id) {
        return paymentStaffDao.findById(id);
    }

    @Override
    public Optional<PaymentStaff> create(PaymentStaff body) {
        return Optional.empty();
    }

    @Override
    public Optional<PaymentStaff> update(PaymentStaff body, Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        paymentStaffDao.deleteById(id);
    }
}
