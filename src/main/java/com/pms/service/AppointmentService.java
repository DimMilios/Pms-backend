package com.pms.service;

import com.pms.dao.AppointmentDao;
import com.pms.dao.DoctorDao;
import com.pms.dao.PatientDao;
import com.pms.dao.StaffDao;
import com.pms.model.appointment.Appointment;
import com.pms.model.appointment.AppointmentKey;
import com.pms.model.patient.Patient;
import com.pms.model.staff.Doctor;
import com.pms.model.staff.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * The type Appointment service.
 */
@Service
@Transactional
public class AppointmentService {

    private final AppointmentDao appointmentDao;
    private final DoctorDao doctorDao;
    private final PatientDao patientDao;

    /**
     * Instantiates a new Appointment service.
     *
     * @param appointmentDao the appointment dao
     * @param doctorDao      the doctor dao
     * @param patientDao     the patient dao
     */
    @Autowired
    public AppointmentService(AppointmentDao appointmentDao,
                              DoctorDao doctorDao,
                              PatientDao patientDao) {
        this.appointmentDao = appointmentDao;
        this.doctorDao = doctorDao;
        this.patientDao = patientDao;
    }

    /**
     * Select by doctor id and ssn optional.
     *
     * @param doctorId the doctor id
     * @param ssn      the ssn
     * @return the optional
     */
    public Optional<Appointment> selectByDoctorIdAndSsn(Long doctorId, Long ssn) {
        Optional<Doctor> doctor = doctorDao.findById(doctorId);
        Optional<Patient> patient = patientDao.findBySsn(ssn);

        return appointmentDao.findByDoctorAndPatient(doctor.get(), patient.get());
    }

    /**
     * Select not date and time optional.
     *
     * @param date the date
     * @param time the time
     * @return the optional
     */
    public Optional<Doctor> selectNotDateAndTime(LocalDate date, LocalTime time) {
//        Long doctorId = appointmentDao.findFirstByAppointmentDateIsNotAndAppointmentTimeIsNot(date, time);
        return Optional.of(appointmentDao.findFirstByAppointmentDateNotAndAppointmentTimeNot(date, time).getDoctor());
//        return doctorDao.findById(doctorId);
    }

    public Optional<Doctor> selectNotDate(LocalDate date) {
        return Optional.of(appointmentDao.findFirstByAppointmentDateNot(date).getDoctor());
    }

    /**
     * Select by doctor id optional.
     *
     * @param doctorId the doctor id
     * @return the optional
     */
    public Optional<Appointment> selectByDoctorId(Long doctorId) {
        return doctorDao
                .findById(doctorId)
                .map(appointmentDao::findByDoctor);
    }

    /**
     * Select by patient ssn optional.
     *
     * @param ssn the ssn
     * @return the optional
     */
    public Optional<Appointment> selectByPatientSsn(Long ssn) {
        return patientDao
                .findBySsn(ssn)
                .map(appointmentDao::findByPatient);
    }

    /**
     * Delete by id.
     *
     * @param doctorId the doctor id
     */
    public void deleteById(Long doctorId) {
        Optional<Doctor> doctor = doctorDao.findById(doctorId);

        appointmentDao.deleteByDoctor(doctor.get());
    }

    /**
     * Delete by ssn.
     *
     * @param ssn the ssn
     */
    public void deleteBySsn(Long ssn) {
        Optional<Patient> patient = patientDao.findBySsn(ssn);

        appointmentDao.deleteByPatient(patient.get());
    }

    /**
     * Delete by date and time.
     *
     * @param date the date
     * @param time the time
     */
    public void deleteByDateAndTime(LocalDate date, LocalTime time) {
        appointmentDao.deleteByAppointmentDateAndAppointmentTime(date, time);
    }

    /**
     * Create appointment optional.
     *
     * @param doctorId the doctor id
     * @param ssn      the ssn
     * @param date     the date
     * @param time     the time
     * @return the optional
     */
    public Optional<Appointment> createAppointment(Long doctorId,
                                                   Long ssn,
                                                   LocalDate date,
                                                   LocalTime time) {
        Optional<Doctor> doctor = doctorDao.findById(doctorId);
        Optional<Patient> patient = patientDao.findBySsn(ssn);

        Appointment appointment = new Appointment();

        appointment.setPatient(patient.get());
        appointment.setDoctor(doctor.get());
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
//        appointment.setAppointmentDate(LocalDate.parse("2020-02-03"));
//        appointment.setAppointmentTime(LocalTime.parse("15:30:00"));
//        appointment.setAppointmentDate(new Timestamp(timeNow));

        return Optional.of(appointmentDao.save(appointment));
    }

}
