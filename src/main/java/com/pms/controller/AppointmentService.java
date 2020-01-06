package com.pms.controller;

import com.pms.dao.AppointmentDao;
import com.pms.dao.PatientDao;
import com.pms.dao.StaffDao;
import com.pms.model.*;
import com.pms.model.patient.Patient;
import com.pms.model.staff.Doctor;
import com.pms.model.staff.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class AppointmentService {

    private AppointmentDao appointmentDao;
    private StaffDao staffDao;
    private PatientDao patientDao;

    @Autowired
    public AppointmentService(AppointmentDao appointmentDao, StaffDao staffDao, PatientDao patientDao) {
        this.appointmentDao = appointmentDao;
        this.staffDao = staffDao;
        this.patientDao = patientDao;
    }

    public Optional<Appointment> selectByDoctorIdAndSsn(Long doctorId, Long ssn) {
        Optional<Staff> doctor = staffDao.findById(doctorId);
        Optional<Patient> patient = patientDao.findBySsn(ssn);

        return appointmentDao.findByDoctorAndPatient((Doctor) doctor.get(), patient.get());
    }

//    public Optional<Appointment> createAppointment(Doctor doctor, Patient patient) {
//        Appointment appointment = new Appointment();
//        AppointmentKey key = new AppointmentKey();
//
//        key.setPatientSsn(patient.getSsn());
//        key.setDoctorId(doctor.getId());
//
//        appointment.setId(key);
//        appointment.setPatient(patient);
//        appointment.setDoctor(doctor);
////        appointment.setAppointmentDate(timestamp);
//        System.out.println(appointment.toString());
//
//        return Optional.ofNullable(appointment);
//    }

    public Optional<Appointment> createAppointment(Long doctorId, Long ssn) {
        Optional<Staff> doctor = staffDao.findById(doctorId);
        Optional<Patient> patient = patientDao.findBySsn(ssn);

        Appointment appointment = new Appointment();
        AppointmentKey key = new AppointmentKey();

        key.setPatientSsn(ssn);
        key.setDoctorId(doctorId);

        long timeNow = System.currentTimeMillis();


        appointment.setId(key);
        appointment.setPatient(patient.get());
        appointment.setDoctor((Doctor) doctor.get());
        appointment.setAppointmentDate(new Timestamp(timeNow));

        return Optional.of(appointment);
    }
}
