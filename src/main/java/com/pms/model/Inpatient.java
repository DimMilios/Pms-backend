package com.pms.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "inpatients")
public class Inpatient extends Patient {

    private Timestamp admitDate;
    private Date dischargeDate;
    private String currentDiagnosis;

    public Timestamp getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(Timestamp admitDate) {
        this.admitDate = admitDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getCurrentDiagnosis() {
        return currentDiagnosis;
    }

    public void setCurrentDiagnosis(String currentDiagnosis) {
        this.currentDiagnosis = currentDiagnosis;
    }

    @Override
    public String toString() {
        return "Inpatient{" +
                "admitDate=" + admitDate +
                ", dischargeDate=" + dischargeDate +
                ", currentDiagnosis='" + currentDiagnosis + '\'' +
                '}';
    }
}
