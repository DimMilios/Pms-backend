package com.pms.dao;

import com.pms.model.appointment.Appointment;
import com.pms.model.appointment.AppointmentKey;
import com.pms.model.staff.Doctor;
import com.pms.model.patient.Patient;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;

/**
 * The interface Appointment dao.
 */
@Repository
public interface AppointmentDao extends CrudRepository<Appointment, Long> {

    /**
     * Find by doctor and patient optional.
     *
     * @param doctor  the doctor
     * @param patient the patient
     * @return the optional
     */
    Optional<Appointment> findByDoctorAndPatient(Doctor doctor, Patient patient);

    /**
     * Find by patient appointment.
     *
     * @param patient the patient
     * @return the appointment
     */
    Appointment findByPatient(Patient patient);

    /**
     * Find by doctor appointment.
     *
     * @param doctor the doctor
     * @return the appointment
     */
    Appointment findByDoctor(Doctor doctor);

    /**
     * Find by appointment date and appointment time appointment.
     *
     * @param date the date
     * @param time the time
     * @return the appointment
     */
    Appointment findByAppointmentDateAndAppointmentTime(LocalDate date, LocalTime time);

    /**
     * Find by appointment date is not and and appointment time is not long.
     *
     * @param date the date
     * @param time the time
     * @return the long
     */
//    @Query(value = "" +
//            "SELECT doctor.id FROM appointments " +
//            "WHERE appointmentDate <> :date AND appointmentTime <> :time")
    Appointment findFirstByAppointmentDateNotAndAppointmentTimeNot(LocalDate date, LocalTime time);

    Appointment findFirstByAppointmentDateNot(LocalDate date);

    /**
     * Delete by doctor.
     *
     * @param doctor the doctor
     */
    void deleteByDoctor(Object doctor);

    /**
     * Delete by patient.
     *
     * @param patient the patient
     */
    void deleteByPatient(Patient patient);

    /**
     * Delete by appointment date and appointment time.
     *
     * @param date the date
     * @param time the time
     */
    void deleteByAppointmentDateAndAppointmentTime(LocalDate date, LocalTime time);
}
