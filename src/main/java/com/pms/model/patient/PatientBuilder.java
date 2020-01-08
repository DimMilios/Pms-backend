package com.pms.model.patient;

import com.pms.model.appointment.Appointment;
import com.pms.model.FullName;
import com.pms.model.Sex;
import com.pms.model.userprofile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class PatientBuilder implements IPatientBuilder {

    private List<Consumer<Patient>> operations;

    private static PatientBuilder INSTANCE;

    public static PatientBuilder patient() {
        if (INSTANCE == null) {
            INSTANCE = new PatientBuilder();
        }

        return INSTANCE;
    }

    private PatientBuilder() {
        this.operations = new ArrayList<>();
    }

    @Override
    public IPatientBuilder withSsn(Long ssn) {
        operations.add(patient -> patient.setSsn(ssn));
        return this;
    }

    @Override
    public IPatientBuilder withOccupation(String occupation) {
        operations.add(patient -> patient.setOccupation(occupation));
        return this;
    }

    @Override
    public IPatientBuilder withSex(String sex) {
        operations.add(patient -> patient.setSex(Sex.toSex(sex)));
        return this;
    }

    @Override
    public IPatientBuilder withUserProfile(UserProfile userProfile) {
        operations.add(patient -> patient.setUserProfile(userProfile));
        return this;
    }

    @Override
    public IPatientBuilder withFullName(FullName fullname) {
        operations.add(patient -> patient.setFullName(fullname));
        return this;
    }

    @Override
    public IPatientBuilder withAppointments(Set<Appointment> appointments) {
        operations.add(patient -> patient.setAppointments(appointments));
        return this;
    }

    @Override
    public Patient build() {
        Patient patient = new Patient();
        operations.forEach(operation -> operation.accept(patient));
        return patient;
    }
}
