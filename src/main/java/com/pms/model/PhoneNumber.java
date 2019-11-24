package com.pms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "staff_phone_no")
public class PhoneNumber implements Serializable {

    @Id
    @Column(name = "staff_id", nullable = false)
    private Long staffId;

    @Id
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @ManyToOne
    @MapsId("staff_id")
    private Staff staff;

    public PhoneNumber() {
    }

    public PhoneNumber(Long staffId, String phoneNumber, Staff staff) {
        this.staffId = staffId;
        this.phoneNumber = phoneNumber;
        this.staff = staff;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
