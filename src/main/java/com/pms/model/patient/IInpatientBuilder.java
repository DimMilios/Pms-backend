package com.pms.model.patient;

import com.pms.model.appointment.Appointment;
import com.pms.model.FullName;
import com.pms.model.userprofile.UserProfile;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

/**
 * The interface Inpatient builder.
 */
public interface IInpatientBuilder extends IPatientBuilder {

    IInpatientBuilder withSsn(Long ssn);

    IInpatientBuilder withOccupation(String occupation);

    IInpatientBuilder withSex(String sex);

    IInpatientBuilder withUserProfile(UserProfile userProfile);

    IInpatientBuilder withFullName(FullName fullname);

    IInpatientBuilder withBirthDate(LocalDate birthDate);

    /**
     * With admit date inpatient builder.
     *
     * @param admitDate the admit date
     * @return the inpatient builder
     */
    IInpatientBuilder withAdmitDate(Timestamp admitDate);

    /**
     * With discharge date inpatient builder.
     *
     * @param dischargeDate the discharge date
     * @return the inpatient builder
     */
    IInpatientBuilder withDischargeDate(Date dischargeDate);

    /**
     * With current diagnosis inpatient builder.
     *
     * @param currentDiagnosis the current diagnosis
     * @return the inpatient builder
     */
    IInpatientBuilder withCurrentDiagnosis(String currentDiagnosis);

    Inpatient build();
}
