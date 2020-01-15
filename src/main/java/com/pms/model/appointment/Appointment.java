package com.pms.model.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pms.model.patient.Patient;
import com.pms.model.staff.Doctor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "appointments")
public class Appointment {

    @EmbeddedId
    private AppointmentKey id;

    @ManyToOne
    @MapsId("doctor_id")
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctor;

    @ManyToOne
    @MapsId("ssn")
    @JoinColumn(name = "ssn")
    private Patient patient;

    private Timestamp appointmentDate;

    public void setId(AppointmentKey id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", appointmentDate=" + appointmentDate +
                '}';
    }
}
