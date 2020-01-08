package com.pms.model.appointment;

import com.pms.model.patient.Patient;
import com.pms.model.staff.Doctor;
import com.pms.model.userprofile.UserProfile;
import com.pms.model.userprofile.UserProfileBuilder;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Consumer;

public class AppointmentBuilder implements IAppointmentBuilder {

    private List<Consumer<Appointment>> operations;

    private static AppointmentBuilder INSTANCE;

    public static AppointmentBuilder appointment() {
        if (INSTANCE == null) {
            INSTANCE = new AppointmentBuilder();
        }

        return INSTANCE;
    }

    @Override
    public IAppointmentBuilder withId(AppointmentKey key) {
        operations.add(appointment -> appointment.setId(key));
        return this;
    }

    @Override
    public IAppointmentBuilder withDoctor(Doctor doctor) {
        operations.add(appointment -> appointment.setDoctor(doctor));
        return this;
    }

    @Override
    public IAppointmentBuilder withPatient(Patient patient) {
        operations.add(appointment -> appointment.setPatient(patient));
        return this;
    }

    @Override
    public IAppointmentBuilder withAppointmentDate(Timestamp appointmentDate) {
        operations.add(appointment -> appointment.setAppointmentDate(appointmentDate));
        return this;
    }

    @Override
    public Appointment build() {
        Appointment appointment = new Appointment();
        operations.forEach(operation -> operation.accept(appointment));
        return appointment;
    }
}
