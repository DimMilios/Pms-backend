package com.pms.model.appointment;

import com.pms.model.patient.Patient;
import com.pms.model.staff.Doctor;

import java.sql.Timestamp;

public interface IAppointmentBuilder {

    IAppointmentBuilder withId(AppointmentKey key);

    IAppointmentBuilder withDoctor(Doctor doctor);

    IAppointmentBuilder withPatient(Patient patient);

    IAppointmentBuilder withAppointmentDate(Timestamp appointmentDate);

    Appointment build();
}
