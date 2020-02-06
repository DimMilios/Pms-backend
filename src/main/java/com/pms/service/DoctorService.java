package com.pms.service;

import com.pms.dao.DoctorDao;
import com.pms.model.staff.*;
import com.pms.model.userprofile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Doctor service.
 */
@Service
public class DoctorService implements GenericService<Doctor> {

    private final DoctorDao doctorDao;
    private final UserProfileService userProfileService;
    private final StaffMapper staffMapper;

    /**
     * Instantiates a new Doctor service.
     *
     * @param doctorDao          the doctor dao
     * @param userProfileService the user profile service
     * @param staffMapper        the staff mapper
     */
    @Autowired
    public DoctorService(DoctorDao doctorDao,
                         UserProfileService userProfileService,
                         StaffMapper staffMapper) {
        this.doctorDao = doctorDao;
        this.userProfileService = userProfileService;
        this.staffMapper = staffMapper;
    }

    @Override
    public Iterable<Doctor> getAll() {
        return doctorDao.findAll();
    }

    @Override
    public Optional<Doctor> getById(Long id) {
        return doctorDao.findById(id);
    }

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    public Optional<Doctor> getByUsername(String username) {
        return doctorDao.findByUserProfileUsername(username);
    }

    @Override
    public Optional<Doctor> create(Doctor body) {
        return createBuild(body);
    }

    @Override
    public Optional<Doctor> update(Doctor body, Long id) {
        return Optional.of(updateBuild(body, id));
    }

    @Override
    public void delete(Long id) {
        doctorDao.deleteById(id);
    }

    private Optional<Doctor> createBuild(Doctor Doctor) {
        UserProfile profileToAdd = Doctor.getUserProfile();

        Optional<Staff> staff = userProfileService.createProfile(profileToAdd)
                .map(profile -> StaffBuilder.staff()
                        .withUserProfile(profile)
                        .withFullName(Doctor.getFullName())
                        .withAddress(Doctor.getAddress())
                        .withPhoneNumbers(Doctor.getPhoneNumbers())
                        .build());

        return staff
                .map(staffMapper::asDoctor)
                .map(doctorDao::save);
    }

    private Doctor updateBuild(Doctor staff, Long id) {
        Staff build = StaffBuilder
                .staff()
                .withUserProfile(staff.getUserProfile())
                .withAddress(staff.getAddress())
                .withPhoneNumbers(staff.getPhoneNumbers())
                .withId(id)
                .build();

        return staffMapper.asDoctor(build);
    }
}
