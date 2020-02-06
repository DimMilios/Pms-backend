package com.pms.service;

import com.pms.dao.LabStaffDao;
import com.pms.dao.StaffDao;
import com.pms.model.staff.LabStaff;
import com.pms.model.staff.Staff;
import com.pms.model.staff.StaffBuilder;
import com.pms.model.staff.StaffMapper;
import com.pms.model.userprofile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Lab staff service.
 */
@Service
public class LabStaffService implements GenericService<LabStaff> {

    private final LabStaffDao labStaffDao;
    private final UserProfileService userProfileService;
    private final StaffMapper staffMapper;

    /**
     * Instantiates a new Lab staff service.
     *
     * @param labStaffDao        the lab staff dao
     * @param userProfileService the user profile service
     * @param staffMapper        the staff mapper
     */
    @Autowired
    public LabStaffService(LabStaffDao labStaffDao,
                           UserProfileService userProfileService,
                           StaffMapper staffMapper) {
        this.labStaffDao = labStaffDao;
        this.userProfileService = userProfileService;
        this.staffMapper = staffMapper;
    }

    @Override
    public Iterable<LabStaff> getAll() {
        return labStaffDao.findAll();
    }

    @Override
    public Optional<LabStaff> getById(Long id) {
        return labStaffDao.findById(id);
    }

    public Optional<LabStaff> getByUsername(String username) {
        return labStaffDao.findByUserProfileUsername(username);
    }


    @Override
    public Optional<LabStaff> create(LabStaff body) {
        return createBuild(body);
    }

    @Override
    public Optional<LabStaff> update(LabStaff body, Long id) {
        return updateBuild(body, id);
    }

    @Override
    public void delete(Long id) {
        labStaffDao.deleteById(id);
    }

    private Optional<LabStaff> createBuild(LabStaff labStaff) {
        UserProfile profileToAdd = labStaff.getUserProfile();

        Optional<Staff> staff = userProfileService.createProfile(profileToAdd)
                .map(profile -> StaffBuilder.staff()
                        .withUserProfile(profile)
                        .withFullName(labStaff.getFullName())
                        .withAddress(labStaff.getAddress())
                        .withPhoneNumbers(labStaff.getPhoneNumbers())
                        .build());

        return staff
                .map(staffMapper::asLabStaff)
                .map(labStaffDao::save);
    }

    private Optional<LabStaff> updateBuild(LabStaff staff, Long id) {
        UserProfile profileToAdd = staff.getUserProfile();
        Optional<UserProfile> profile = userProfileService.updateProfile(profileToAdd, profileToAdd.getId());

        Optional<LabStaff> labStaff = labStaffDao.findById(id);

        return labStaff.map(lab -> {
            lab.setUserProfile(profile.get());
            lab.setFullName(staff.getFullName());
            lab.setAddress(staff.getAddress());
            lab.setPhoneNumbers(staff.getPhoneNumbers());
            return lab;
        }).map(labStaffDao::save);
    }
}
