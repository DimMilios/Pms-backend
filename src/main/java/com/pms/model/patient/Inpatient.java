package com.pms.model.patient;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The type Inpatient.
 */
@Entity(name = "inpatients")
public class Inpatient extends Patient {

    private LocalDateTime admitDate;

    private LocalDate dischargeDate;

    private String currentDiagnosis;

    /**
     * Gets admit date.
     *
     * @return the admit date
     */
    public LocalDateTime getAdmitDate() {
        return admitDate;
    }

    /**
     * Sets admit date.
     *
     * @param admitDate the admit date
     */
    public void setAdmitDate(LocalDateTime admitDate) {
        this.admitDate = admitDate;
    }

    /**
     * Gets discharge date.
     *
     * @return the discharge date
     */
    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    /**
     * Sets discharge date.
     *
     * @param dischargeDate the discharge date
     */
    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    /**
     * Gets current diagnosis.
     *
     * @return the current diagnosis
     */
    public String getCurrentDiagnosis() {
        return currentDiagnosis;
    }

    /**
     * Sets current diagnosis.
     *
     * @param currentDiagnosis the current diagnosis
     */
    public void setCurrentDiagnosis(String currentDiagnosis) {
        this.currentDiagnosis = currentDiagnosis;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Inpatient{" +
                "admitDate=" + admitDate +
                ", dischargeDate=" + dischargeDate +
                ", currentDiagnosis='" + currentDiagnosis + '\'' +
                '}';
    }
}
