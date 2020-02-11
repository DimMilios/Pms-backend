package com.pms.model.staff;

import org.springframework.stereotype.Component;

@Component
public class StaffFactory {

    public Staff getStaff(StaffType staffType) {
        switch (staffType) {
            case DOCTOR:
                return new Doctor();
            case LABSTAFF:
                return new LabStaff();
            case PAYMENTSTAFF:
                return new PaymentStaff();
            case RECEPTIONIST:
                return new Receptionist();
            default:
                return null;
        }
    }
}
