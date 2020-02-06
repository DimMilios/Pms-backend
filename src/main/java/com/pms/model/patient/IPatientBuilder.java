package com.pms.model.patient;

import com.pms.model.appointment.Appointment;
import com.pms.model.FullName;
import com.pms.model.userprofile.UserProfile;

import java.time.LocalDate;
import java.util.Set;

/**
 * The interface Patient builder.
 */
public interface IPatientBuilder {

    /**
     * With ssn patient builder.
     *
     * @param ssn the ssn
     * @return the patient builder
     */
    IPatientBuilder withSsn(Long ssn);

    /**
     * With occupation patient builder.
     *
     * @param occupation the occupation
     * @return the patient builder
     */
    IPatientBuilder withOccupation(String occupation);

    /**
     * With sex patient builder.
     *
     * @param sex the sex
     * @return the patient builder
     */
    IPatientBuilder withSex(String sex);

    /**
     * With user profile patient builder.
     *
     * @param userProfile the user profile
     * @return the patient builder
     */
    IPatientBuilder withUserProfile(UserProfile userProfile);

    /**
     * With full name patient builder.
     *
     * @param fullname the fullname
     * @return the patient builder
     */
    IPatientBuilder withFullName(FullName fullname);

    /**
     * With birth date patient builder.
     *
     * @param birthDate the birth date
     * @return the patient builder
     */
    IPatientBuilder withBirthDate(LocalDate birthDate);

    /**
     * Build patient.
     *
     * @return the patient
     */
    Patient build();

//    IPatientBuilder withAddress(Address address);
}
