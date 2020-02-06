package com.pms.model;

import org.springframework.lang.Nullable;

import javax.persistence.Embeddable;

/**
 * The type Address.
 */
@Embeddable
public class Address {

    @Nullable
    private String city;
    @Nullable
    private String streetAddress;
    private int zipCode = 0;

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets street address.
     *
     * @return the street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets street address.
     *
     * @param address the address
     */
    public void setStreetAddress(String address) {
        this.streetAddress = address;
    }


    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
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
