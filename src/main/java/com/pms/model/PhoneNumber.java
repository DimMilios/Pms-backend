package com.pms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Embeddable
//@Table(name = "staff_phone_no")
public class PhoneNumber {

//    @Id
//    @Column(name = "staff_id", nullable = false)
//    private Long staffId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

//    private Staff staff;

    public PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
