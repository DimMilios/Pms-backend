package com.pms.service;

import com.pms.dao.PatientDao;
import com.pms.model.patient.Patient;
import com.pms.model.patient.PatientBuilder;
import com.pms.model.userprofile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


/**
 * The type Patient service.
 */
@Service
@Transactional
public class PatientService implements GenericService<Patient> {

    private PatientDao patientDao;
    private final UserProfileService userProfileService;

    /**
     * Instantiates a new Patient service.
     *
     * @param patientDao         the patient dao
     * @param userProfileService the user profile service
     */
    @Autowired
    public PatientService(PatientDao patientDao,
                          UserProfileService userProfileService) {
        this.patientDao = patientDao;
        this.userProfileService = userProfileService;
    }


    @Override
    public Iterable<Patient> getAll() {
        return patientDao.findAll();
    }

    @Override
    public Optional<Patient> getById(Long id) {
        return patientDao.findBySsn(id);
    }

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    public Optional<Patient> getByUsername(String username) {
        return patientDao.findByUserProfileUsername(username);
    }

    @Override
    public Optional<Patient> create(Patient patient) {
//        Patient patientToAdd = getBuild(patient, patient.getSsn());
//
//        return Optional.of(patientDao.save(patientToAdd));
        return createBuild(patient);
    }

    @Override
    public Optional<Patient> update(Patient patient, Long id) {
        assert patient.getUserProfile() != null;
        Optional<UserProfile> profile = userProfileService.updateProfile(
                patient.getUserProfile(), patient.getUserProfile().getId());

        Optional<Patient> patient1 = patientDao.findBySsn(id)
                .map(p -> {
                    p.setSsn(patient.getSsn());
                    p.setUserProfile(profile.get());
                    p.setBirthDate(patient.getBirthDate());
                    p.setFullName(patient.getFullName());
                    p.setSex(patient.getSex());
                    p.setOccupation(patient.getOccupation());
                    return p;
                });

//        return patientDao.save(patient1.get());
//        Patient patientToAdd = getBuild(patient, id);
//
        return Optional.of(patientDao.save(patient1.get()));
    }

    @Override
    public void delete(Long id) throws EmptyResultDataAccessException {
            patientDao.deleteById(id);
    }

    private Optional<Patient> createBuild(Patient patient) {
        UserProfile profileToAdd = patient.getUserProfile();

        assert profileToAdd != null;
        Optional<Patient> p1 = userProfileService.createProfile(profileToAdd)
                .map(profile -> PatientBuilder.patient()
                        .withSsn(patient.getSsn())
                        .withOccupation(patient.getOccupation())
                        .withSex(patient.getSex().toString())
                        .withUserProfile(profile)
                        .withBirthDate(patient.getBirthDate())
                        .withFullName(patient.getFullName())
                        .build());

        return p1.map(patientDao::save);
    }

    private Patient getBuild(Patient patient, Long id) {
        return PatientBuilder
                .patient()
                .withSsn(id)
                .withFullName(patient.getFullName())
                .withOccupation(patient.getOccupation())
                .withUserProfile(patient.getUserProfile())
                .withSex(patient.getSex().toString())
                .withBirthDate(patient.getBirthDate())
//                .withAppointments(patient.getAppointments())
                .build();
    }
}
