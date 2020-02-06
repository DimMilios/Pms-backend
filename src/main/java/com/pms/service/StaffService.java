package com.pms.service;

import com.pms.dao.StaffDao;
import com.pms.model.patient.Patient;
import com.pms.model.staff.LabStaff;
import com.pms.model.staff.Staff;
import com.pms.model.staff.StaffBuilder;
import com.pms.model.userprofile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Staff service.
 */
@Service
public class StaffService implements GenericService<Staff> {

    private final StaffDao staffDao;
    private final UserProfileService userProfileService;

    /**
     * Instantiates a new Staff service.
     *
     * @param staffDao           the staff dao
     * @param userProfileService the user profile service
     */
    @Autowired
    public StaffService(StaffDao staffDao, UserProfileService userProfileService) {
        this.staffDao = staffDao;
        this.userProfileService = userProfileService;
    }

    @Override
    public Iterable<Staff> getAll() {
        return staffDao.findAll();
    }

    @Override
    public Optional<Staff> getById(Long id) {
        return staffDao.findById(id);
    }

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    public Optional<Staff> getByUsername(String username) {
        return staffDao.findByUserProfileUsername(username);
    }

    @Override
    public Optional<Staff> create(Staff body) {
        UserProfile profileToAdd = body.getUserProfile();
        Optional<Staff> staff = userProfileService.createProfile(profileToAdd)
                .map(profile -> StaffBuilder.staff()
                        .withUserProfile(profile)
                        .withFullName(body.getFullName())
                        .withAddress(body.getAddress())
                        .withPhoneNumbers(body.getPhoneNumbers())
                        .build());
       return staff.map(staffDao::save);
    }

    @Override
    public Optional<Staff> update(Staff body, Long id) {
        Staff staff = getBuild(body, id);

        return Optional.of(staffDao.save(staff));
    }

    @Override
    public void delete(Long id) {
        staffDao.deleteById(id);
    }

    private Staff getBuild(Staff staff, Long id) {
        return StaffBuilder.staff()
                .withUserProfile(staff.getUserProfile())
                .withFullName(staff.getFullName())
                .withAddress(staff.getAddress())
                .withPhoneNumbers(staff.getPhoneNumbers())
                .withId(id)
                .build();
    }
}
