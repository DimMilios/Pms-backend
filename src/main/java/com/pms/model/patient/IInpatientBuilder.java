package com.pms.model.patient;

import com.pms.model.appointment.Appointment;
import com.pms.model.FullName;
import com.pms.model.userprofile.UserProfile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

public interface IInpatientBuilder extends IPatientBuilder {

    IInpatientBuilder withSsn(Long ssn);

    IInpatientBuilder withOccupation(String occupation);

    IInpatientBuilder withSex(String sex);

    IInpatientBuilder withUserProfile(UserProfile userProfile);

    IInpatientBuilder withFullName(FullName fullname);

    IInpatientBuilder withAppointments(Set<Appointment> appointments);

    IInpatientBuilder withAdmitDate(Timestamp admitDate);

    IInpatientBuilder withDischargeDate(Date dischargeDate);

    IInpatientBuilder withCurrentDiagnosis(String currentDiagnosis);

    Inpatient build();
}
