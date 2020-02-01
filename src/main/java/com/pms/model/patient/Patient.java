package com.pms.model.patient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pms.model.appointment.Appointment;
import com.pms.model.FullName;
import com.pms.model.Sex;
import com.pms.model.userprofile.UserProfile;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Patient {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private Long ssn;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    private String occupation;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Nullable
    @Embedded
    @JsonUnwrapped
    private FullName fullName;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    Set<Appointment> appointments;

    public Long getSsn() {
        return ssn;
    }

    public void setSsn(Long ssn) {
        this.ssn = ssn;
    }

    @Nullable
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(@Nullable UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Nullable
    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(@Nullable FullName fullName) {
        this.fullName = fullName;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "ssn=" + ssn +
                ", userProfile=" + userProfile +
                ", occupation='" + occupation + '\'' +
                ", sex=" + sex +
                ", fullName=" + fullName +
                ", appointsments=" + appointments +
                '}';
    }
}
