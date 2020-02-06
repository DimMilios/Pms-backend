package com.pms.model.prescription;

import com.pms.model.patient.Patient;
import com.pms.model.staff.Doctor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * The type Prescription.
 */
@Entity(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "ssn")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @Column(name = "prescription_date")
    private LocalDate prescriptionDate;

    @Column(name = "dosage")
    private int dosage;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets doctor.
     *
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets doctor.
     *
     * @param doctor the doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Gets patient.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets patient.
     *
     * @param patient the patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Gets medicine.
     *
     * @return the medicine
     */
    public Medicine getMedicine() {
        return medicine;
    }

    /**
     * Sets medicine.
     *
     * @param medicine the medicine
     */
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    /**
     * Gets prescription date.
     *
     * @return the prescription date
     */
    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    /**
     * Sets prescription date.
     *
     * @param prescriptionDate the prescription date
     */
    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    /**
     * Gets dosage.
     *
     * @return the dosage
     */
    public int getDosage() {
        return dosage;
    }

    /**
     * Sets dosage.
     *
     * @param dosage the dosage
     */
    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", medicine=" + medicine +
                ", prescriptionDate=" + prescriptionDate +
                ", dosage=" + dosage +
                '}';
    }
}
