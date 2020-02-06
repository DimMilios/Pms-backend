package com.pms.service;

import com.pms.dao.PaymentStaffDao;
import com.pms.model.staff.*;
import com.pms.model.userprofile.UserProfile;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Payment staff service.
 */
@Service
public class PaymentStaffService implements GenericService<PaymentStaff> {

    private final PaymentStaffDao paymentStaffDao;
    private final UserProfileService userProfileService;
    private final StaffMapper staffMapper;

    /**
     * Instantiates a new Payment staff service.
     *
     * @param paymentStaffDao    the payment staff dao
     * @param userProfileService the user profile service
     * @param staffMapper        the staff mapper
     */
    public PaymentStaffService(PaymentStaffDao paymentStaffDao,
                               UserProfileService userProfileService,
                               StaffMapper staffMapper) {
        this.paymentStaffDao = paymentStaffDao;
        this.userProfileService = userProfileService;
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

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    public Optional<PaymentStaff> getByUsername(String username) {
        return paymentStaffDao.findByUserProfileUsername(username);
    }


    @Override
    public Optional<PaymentStaff> create(PaymentStaff body) {
        return getBuild(body, body.getId());
    }

    @Override
    public Optional<PaymentStaff> update(PaymentStaff body, Long id) {
        return getBuild(body, id);
    }

    @Override
    public void delete(Long id) {
        paymentStaffDao.deleteById(id);
    }

    private Optional<PaymentStaff> getBuild(Staff staff, Long id) {
        UserProfile profileToAdd = staff.getUserProfile();

        Optional<Staff> paymentStaff = userProfileService.createProfile(profileToAdd)
                .map(profile -> StaffBuilder.staff()
                        .withUserProfile(profile)
                        .withFullName(staff.getFullName())
                        .withAddress(staff.getAddress())
                        .withPhoneNumbers(staff.getPhoneNumbers())
                        .withId(id)
                        .build());

        return paymentStaff
                .map(staffMapper::asPaymentStaff)
                .map(paymentStaffDao::save);
    }
}
