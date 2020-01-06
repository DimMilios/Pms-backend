package com.pms.model.patient;

import com.pms.model.Appointment;
import com.pms.model.FullName;
import com.pms.model.Sex;
import com.pms.model.userprofile.UserProfile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class InpatientBuilder implements IInpatientBuilder {

    private List<Consumer<Inpatient>> operations;

    private static InpatientBuilder INSTANCE;

    public static InpatientBuilder inpatient() {
        if (INSTANCE == null) {
            INSTANCE = new InpatientBuilder();
        }

        return INSTANCE;
    }

    private InpatientBuilder() {
        this.operations = new ArrayList<>();
    }

    @Override
    public IInpatientBuilder withAdmitDate(Timestamp admitDate) {
        operations.add(patient -> patient.setAdmitDate(admitDate));
        return this;
    }

    @Override
    public IInpatientBuilder withDischargeDate(Date dischargeDate) {
        operations.add(patient -> patient.setDischargeDate(dischargeDate));
        return this;
    }

    @Override
    public IInpatientBuilder withCurrentDiagnosis(String currentDiagnosis) {
        operations.add(patient -> patient.setCurrentDiagnosis(currentDiagnosis));
        return this;
    }

    @Override
    public IInpatientBuilder withSsn(Long ssn) {
        operations.add(patient -> patient.setSsn(ssn));
        return this;
    }

    @Override
    public IInpatientBuilder withOccupation(String occupation) {
        operations.add(patient -> patient.setOccupation(occupation));
        return this;
    }

    @Override
    public IInpatientBuilder withSex(String sex) {
        operations.add(patient -> patient.setSex(Sex.toSex(sex)));
        return this;
    }

    @Override
    public IInpatientBuilder withUserProfile(UserProfile userProfile) {
        operations.add(inpatient -> inpatient.setUserProfile(userProfile));
        return this;
    }

    @Override
    public IInpatientBuilder withFullName(FullName fullname) {
        operations.add(patient -> patient.setFullName(fullname));
        return this;
    }

    @Override
    public IInpatientBuilder withAppointments(Set<Appointment> appointments) {
        operations.add(patient -> patient.setAppointments(appointments));
        return this;
    }

    @Override
    public Inpatient build() {
        Inpatient inpatient = new Inpatient();
        operations.forEach(operation -> operation.accept(inpatient));
        return inpatient;
    }
}
