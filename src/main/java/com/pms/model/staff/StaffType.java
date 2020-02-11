package com.pms.model.staff;

public enum StaffType {
    DOCTOR {
        @Override
        public String toString() {
            return "Doctor";
        }
    },
    LABSTAFF {
        @Override
        public String toString() {
            return "Lab_staff";
        }
    },
    PAYMENTSTAFF {
        @Override
        public String toString() {
            return "Payment_staff";
        }
    },
    RECEPTIONIST {
        @Override
        public String toString() {
            return "Receptionist";
        }
    }
}
