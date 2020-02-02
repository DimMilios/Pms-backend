package com.pms.controller;

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

@Service
@Transactional
public class AppointmentService {

    private final AppointmentDao appointmentDao;
    private final DoctorDao doctorDao;
    private final PatientDao patientDao;

    @Autowired
    public AppointmentService(AppointmentDao appointmentDao,
                              DoctorDao doctorDao,
                              PatientDao patientDao) {
        this.appointmentDao = appointmentDao;
        this.doctorDao = doctorDao;
        this.patientDao = patientDao;
    }

    public Optional<Appointment> selectByDoctorIdAndSsn(Long doctorId, Long ssn) {
        Optional<Doctor> doctor = doctorDao.findById(doctorId);
        Optional<Patient> patient = patientDao.findBySsn(ssn);

        return appointmentDao.findByDoctorAndPatient(doctor.get(), patient.get());
    }

    public Optional<Appointment> selectByDoctorId(Long doctorId) {
        return doctorDao
                .findById(doctorId)
                .map(appointmentDao::findByDoctor);
    }

    public Optional<Appointment> selectByPatientSsn(Long ssn) {
        return patientDao
                .findBySsn(ssn)
                .map(appointmentDao::findByPatient);
    }

    public void deleteById(Long doctorId) {
        Optional<Doctor> doctor = doctorDao.findById(doctorId);

        appointmentDao.deleteByDoctor(doctor.get());
    }

    public void deleteBySsn(Long ssn) {
        Optional<Patient> patient = patientDao.findBySsn(ssn);

        appointmentDao.deleteByPatient(patient.get());
    }

    public void deleteByDateAndTime(LocalDate date, LocalTime time) {
        appointmentDao.deleteByAppointmentDateAndAppointmentTime(date, time);
    }


//    public void deleteByAppointmentDate(Timestamp appointmentDate) {
//        appointmentDao.deleteByAppointmentDate(appointmentDate);
//    }


    public Optional<Appointment> createAppointment(Long doctorId, Long ssn, LocalDate date, LocalTime time) {
        Optional<Doctor> doctor = doctorDao.findById(doctorId);
        Optional<Patient> patient = patientDao.findBySsn(ssn);

        Appointment appointment = new Appointment();
//        AppointmentKey key = new AppointmentKey();
//
//        key.setPatientSsn(ssn);
//        key.setDoctorId(doctorId);

        long timeNow = System.currentTimeMillis();

//        appointment.setId(key);
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
