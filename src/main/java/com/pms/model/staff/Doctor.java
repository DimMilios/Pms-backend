package com.pms.model.staff;

import com.pms.model.appointment.Appointment;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "doctors")
//@PrimaryKeyJoinColumn(name = "id")
public class Doctor extends Staff {

    @OneToMany(mappedBy = "doctor")
    Set<Appointment> appointments;
}
