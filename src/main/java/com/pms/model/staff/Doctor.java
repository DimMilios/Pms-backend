package com.pms.model.staff;

import com.pms.model.appointment.Appointment;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "doctors")
@DiscriminatorValue("Doctor")
//@PrimaryKeyJoinColumn(name = "id")
public class Doctor extends Staff {

    @OneToMany(mappedBy = "doctor")
    Set<Appointment> appointments;

}
