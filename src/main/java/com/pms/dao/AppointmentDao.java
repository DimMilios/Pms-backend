package com.pms.dao;

import com.pms.model.Appointment;
import com.pms.model.AppointmentKey;
import com.pms.model.Doctor;
import com.pms.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentDao extends CrudRepository<Appointment, AppointmentKey> {

    Optional<Appointment> findByDoctorAndPatient(Doctor doctorId, Patient patient);


}
