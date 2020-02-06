package com.pms.model;

import javax.persistence.*;

/**
 * The type Phone number.
 */
@Embeddable
public class PhoneNumber {

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    /**
     * Instantiates a new Phone number.
     */
    public PhoneNumber() {
    }

    /**
     * Instantiates a new Phone number.
     *
     * @param phoneNumber the phone number
     */
    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
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
