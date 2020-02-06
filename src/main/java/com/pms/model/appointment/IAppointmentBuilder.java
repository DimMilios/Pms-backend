package com.pms.model.appointment;

import com.pms.model.patient.Patient;
import com.pms.model.staff.Doctor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The interface Appointment builder.
 */
public interface IAppointmentBuilder {

    /**
     * With doctor appointment builder.
     *
     * @param doctor the doctor
     * @return the appointment builder
     */
    IAppointmentBuilder withDoctor(Doctor doctor);

    /**
     * With patient appointment builder.
     *
     * @param patient the patient
     * @return the appointment builder
     */
    IAppointmentBuilder withPatient(Patient patient);

    /**
     * With appointment date appointment builder.
     *
     * @param appointmentDate the appointment date
     * @return the appointment builder
     */
    IAppointmentBuilder withAppointmentDate(LocalDate appointmentDate);

    /**
     * With appointment time appointment builder.
     *
     * @param appointmentTime the appointment time
     * @return the appointment builder
     */
    IAppointmentBuilder withAppointmentTime(LocalTime appointmentTime);

    /**
     * Build appointment.
     *
     * @return the appointment
     */
    Appointment build();
}
