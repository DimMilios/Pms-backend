package com.pms.model.appointment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AppointmentKey implements Serializable {

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "ssn")
    private Long patientSsn;

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientSsn(Long patientSsn) {
        this.patientSsn = patientSsn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentKey that = (AppointmentKey) o;
        return Objects.equals(doctorId, that.doctorId) &&
                Objects.equals(patientSsn, that.patientSsn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, patientSsn);
    }

    @Override
    public String toString() {
        return "AppointmentKey{" +
                "doctorId=" + doctorId +
                ", patientSsn=" + patientSsn +
                '}';
    }
}
