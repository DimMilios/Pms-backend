package com.pms.model.patient;

import com.pms.model.appointment.Appointment;
import com.pms.model.FullName;
import com.pms.model.userprofile.UserProfile;

import java.util.Set;

public interface IPatientBuilder {

    IPatientBuilder withSsn(Long ssn);

    IPatientBuilder withOccupation(String occupation);

    IPatientBuilder withSex(String sex);

    IPatientBuilder withUserProfile(UserProfile userProfile);

    IPatientBuilder withFullName(FullName fullname);

    IPatientBuilder withAppointments(Set<Appointment> appointments);

    Patient build();

//    IPatientBuilder withAddress(Address address);
}
