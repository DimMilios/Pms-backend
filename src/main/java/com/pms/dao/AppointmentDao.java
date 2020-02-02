package com.pms.dao;

import com.pms.model.appointment.Appointment;
import com.pms.model.appointment.AppointmentKey;
import com.pms.model.staff.Doctor;
import com.pms.model.patient.Patient;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AppointmentDao extends CrudRepository<Appointment, Long> {

    Optional<Appointment> findByDoctorAndPatient(Doctor doctor, Patient patient);

    Appointment findByPatient(Patient patient);

    Appointment findByDoctor(Doctor doctor);

    Appointment findByAppointmentDate(Timestamp timestamp);

    void deleteByDoctor(Object doctor);

    void deleteByPatient(Patient patient);

//    void deleteByAppointmentDate(Timestamp timestamp);
    void deleteByAppointmentDateAndAppointmentTime(LocalDate date, LocalTime time);
}
