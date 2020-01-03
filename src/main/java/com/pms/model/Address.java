package com.pms.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String streetAddress;
    private int zipCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String address) {
        this.streetAddress = address;
    }


    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street address='" + streetAddress + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
