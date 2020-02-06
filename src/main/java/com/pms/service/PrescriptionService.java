package com.pms.service;

import com.pms.dao.DoctorDao;
import com.pms.dao.MedicineDao;
import com.pms.dao.PatientDao;
import com.pms.dao.PrescriptionDao;
import com.pms.model.patient.Patient;
import com.pms.model.prescription.Medicine;
import com.pms.model.prescription.Prescription;
import com.pms.model.staff.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

/**
 * The type Prescription service.
 */
@Service
@Transactional
public class PrescriptionService {

    private final PrescriptionDao prescriptionDao;
    private final DoctorDao doctorDao;
    private final PatientDao patientDao;
    private final MedicineDao medicineDao;

    /**
     * Instantiates a new Prescription service.
     *
     * @param prescriptionDao the prescription dao
     * @param doctorDao       the doctor dao
     * @param patientDao      the patient dao
     * @param medicineDao     the medicine dao
     */
    @Autowired
    public PrescriptionService(PrescriptionDao prescriptionDao,
                               DoctorDao doctorDao,
                               PatientDao patientDao,
                               MedicineDao medicineDao) {
        this.prescriptionDao = prescriptionDao;
        this.doctorDao = doctorDao;
        this.patientDao = patientDao;
        this.medicineDao = medicineDao;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public Iterable<Prescription> getAll() {
        return prescriptionDao.findAll();
    }

    /**
     * Gets by doctor.
     *
     * @param doctorId the doctor id
     * @return the by doctor
     */
    public Optional<Prescription> getByDoctor(Long doctorId) {
        return doctorDao.findById(doctorId)
                .flatMap(prescriptionDao::getByDoctor);
    }

    /**
     * Gets by patient.
     *
     * @param ssn the ssn
     * @return the by patient
     */
    public Optional<Prescription> getByPatient(Long ssn) {
        return patientDao.findById(ssn)
                .flatMap(prescriptionDao::getByPatient);
    }

    /**
     * Gets by date.
     *
     * @param date the date
     * @return the by date
     */
    public Optional<Prescription> getByDate(LocalDate date) {
        return prescriptionDao.getByPrescriptionDate(date);
    }

    /**
     * Create optional.
     *
     * @param prescription the prescription
     * @param date         the date
     * @return the optional
     */
    public Optional<Prescription> create(Prescription prescription, LocalDate date) {
        Optional<Doctor> doctor = doctorDao.findById(prescription.getDoctor().getId());
        Optional<Patient> patient = patientDao.findBySsn(prescription.getPatient().getSsn());
        Optional<Medicine> medicine = medicineDao.findById(prescription.getMedicine().getId());

        Prescription pre = new Prescription();

        pre.setDoctor(doctor.get());
        pre.setPatient(patient.get());
        pre.setMedicine(medicine.get());
        pre.setPrescriptionDate(date);
        pre.setDosage(prescription.getDosage());

        return Optional.of(prescriptionDao.save(pre));
    }

    /**
     * Delete by id.
     *
     * @param prescriptionId the prescription id
     */
    public void deleteById(Long prescriptionId) {
        prescriptionDao.deleteById(prescriptionId);
    }

    /**
     * Delete by patient and date.
     *
     * @param ssn  the ssn
     * @param date the date
     */
    public void deleteByPatientAndDate(Long ssn, LocalDate date) {
        Optional<Patient> patient = patientDao.findBySsn(ssn);

        patient.ifPresent(p -> prescriptionDao.deleteByPatientAndPrescriptionDate(p, date));
    }

    /**
     * Delete by doctor and date.
     *
     * @param doctorId the doctor id
     * @param date     the date
     */
    public void deleteByDoctorAndDate(Long doctorId, LocalDate date) {
        Optional<Doctor> doctor = doctorDao.findById(doctorId);

        prescriptionDao.deleteByDoctorAndPrescriptionDate(doctor.get(), date);
    }
}
