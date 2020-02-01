package com.pms.model.staff;

import org.springframework.stereotype.Component;

@Component
public class StaffMapper {

    public LabStaff asLabStaff(Staff staff) {
        LabStaff labStaff = new LabStaff();
        labStaff.setId(staff.getId());
        labStaff.setUserProfile(staff.getUserProfile());
        labStaff.setFullName(staff.getFullName());
        labStaff.setAddress(staff.getAddress());
        labStaff.setPhoneNumbers(staff.getPhoneNumbers());
        return labStaff;
    }

    public Receptionist asReceptionist(Staff staff) {
        Receptionist receptionist = new Receptionist();
        receptionist.setId(staff.getId());
        receptionist.setUserProfile(staff.getUserProfile());
        receptionist.setFullName(staff.getFullName());
        receptionist.setAddress(staff.getAddress());
        receptionist.setPhoneNumbers(staff.getPhoneNumbers());
        return receptionist;
    }

    public PaymentStaff asPaymentStaff(Staff staff) {
        PaymentStaff paymentStaff = new PaymentStaff();
        paymentStaff.setId(staff.getId());
        paymentStaff.setUserProfile(staff.getUserProfile());
        paymentStaff.setFullName(staff.getFullName());
        paymentStaff.setAddress(staff.getAddress());
        paymentStaff.setPhoneNumbers(staff.getPhoneNumbers());
        return paymentStaff;
    }

}
