package com.pms.model.patient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pms.model.Address;
import com.pms.model.appointment.Appointment;
import com.pms.model.FullName;
import com.pms.model.Sex;
import com.pms.model.prescription.Prescription;
import com.pms.model.userprofile.UserProfile;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

/**
 * The type Patient.
 */
@Entity(name = "patients")
@Inheritance(strategy = InheritanceType.JOINED)
public class Patient {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private Long ssn;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id", unique = true)
    private UserProfile userProfile;

    @NotBlank
    private String occupation;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Nullable
    @Embedded
    @JsonUnwrapped
    private FullName fullName;

//    @Nullable
//    @Embedded
//    @JsonUnwrapped
//    private Address address;

    /**
     * The Appointments.
     */
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    @JsonIgnore
    Set<Appointment> appointments;

    /**
     * The Prescriptions.
     */
    @OneToMany(mappedBy = "patient",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    Set<Prescription> prescriptions;

    /**
     * Gets ssn.
     *
     * @return the ssn
     */
    public Long getSsn() {
        return ssn;
    }

    /**
     * Sets ssn.
     *
     * @param ssn the ssn
     */
    public void setSsn(Long ssn) {
        this.ssn = ssn;
    }

    /**
     * Gets user profile.
     *
     * @return the user profile
     */
    @Nullable
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Sets user profile.
     *
     * @param userProfile the user profile
     */
    public void setUserProfile(@Nullable UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * Gets occupation.
     *
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets occupation.
     *
     * @param occupation the occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Gets sex.
     *
     * @return the sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Sets sex.
     *
     * @param sex the sex
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    @Nullable
    public FullName getFullName() {
        return fullName;
    }

    /**
     * Sets full name.
     *
     * @param fullName the full name
     */
    public void setFullName(@Nullable FullName fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets birth date.
     *
     * @return the birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets birth date.
     *
     * @param birthDate the birth date
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

//    @Nullable
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(@Nullable Address address) {
//        this.address = address;
//    }


    @Override
    public String toString() {
        return "Patient{" +
                "ssn=" + ssn +
                ", userProfile=" + userProfile +
                ", occupation='" + occupation + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", fullName=" + fullName +
//                ", address=" + address +
                ", appointments=" + appointments +
                '}';
    }
}
