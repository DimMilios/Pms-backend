package com.pms.dao;

import com.pms.model.appointment.Appointment;
import com.pms.model.appointment.AppointmentKey;
import com.pms.model.staff.Doctor;
import com.pms.model.patient.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AppointmentDao extends CrudRepository<Appointment, AppointmentKey> {

    Optional<Appointment> findByDoctorAndPatient(Doctor doctorId, Patient patient);
}
