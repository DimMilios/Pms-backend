package com.pms.model.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pms.model.patient.Patient;
import com.pms.model.staff.Doctor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "appointments")
public class Appointment {

//    @EmbeddedId
//    private AppointmentKey id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @MapsId("doctor_id")
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
//    @MapsId("ssn")
    @JoinColumn(name = "ssn")
    private Patient patient;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "appointment_time")
    private LocalTime appointmentTime;

//    public void setId(AppointmentKey id) {
//        this.id = id;
//    }


    public void setId(Long id) {
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

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
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
