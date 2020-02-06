package com.pms.model;

import javax.persistence.Embeddable;

/**
 * The type Full name.
 */
@Embeddable
public class FullName {

    private String firstName;
    private String lastName;
    private String fatherName;

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets father name.
     *
     * @return the father name
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * Sets father name.
     *
     * @param fatherName the father name
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public String toString() {
        return "FullName{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}
