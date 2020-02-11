package com.pms.model.staff;

import com.pms.model.appointment.Appointment;
import com.pms.model.prescription.Prescription;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "doctors")
@DiscriminatorValue("Doctor")
public class Doctor extends Staff {

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    Set<Appointment> appointments;

    @OneToMany(mappedBy = "doctor",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    Set<Prescription> prescriptions;
}
