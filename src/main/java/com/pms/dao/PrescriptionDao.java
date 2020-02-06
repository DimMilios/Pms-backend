package com.pms.dao;

import com.pms.model.patient.Patient;
import com.pms.model.prescription.Prescription;
import com.pms.model.staff.Doctor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * The interface Prescription dao.
 */
@Repository
public interface PrescriptionDao extends CrudRepository<Prescription, Long> {

    /**
     * Gets by doctor.
     *
     * @param doctor the doctor
     * @return the by doctor
     */
    Optional<Prescription> getByDoctor(Doctor doctor);

    /**
     * Gets by patient.
     *
     * @param patient the patient
     * @return the by patient
     */
    Optional<Prescription> getByPatient(Patient patient);

    /**
     * Gets by prescription date.
     *
     * @param date the date
     * @return the by prescription date
     */
    Optional<Prescription> getByPrescriptionDate(LocalDate date);

    @Modifying
    @Query(value = "DELETE FROM prescriptions WHERE id = :id")
    void deleteById(Long id);

    /**
     * Delete by patient and prescription date.
     *
     * @param patient the patient
     * @param date    the date
     */
    @Modifying
    @Query(value = "DELETE FROM prescriptions WHERE patient = :patient AND prescriptionDate = :date ")
    void deleteByPatientAndPrescriptionDate(Patient patient, LocalDate date);

    /**
     * Delete by doctor and prescription date.
     *
     * @param doctor the doctor
     * @param date   the date
     */
    @Modifying
    @Query(value = "DELETE FROM prescriptions WHERE doctor = :doctor AND prescriptionDate = :date ")
    void deleteByDoctorAndPrescriptionDate(Doctor doctor, LocalDate date);
}
