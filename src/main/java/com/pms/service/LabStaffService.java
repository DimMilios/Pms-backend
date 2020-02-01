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

@Service
public class LabStaffService implements GenericService<LabStaff> {

    private final LabStaffDao labStaffDao;
    private final UserProfileService userProfileService;
    private final StaffMapper staffMapper;

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

    @Override
    public Optional<LabStaff> create(LabStaff body) {

        Optional<Staff> labStaff = userProfileService.createProfile(body.getUserProfile())
                .map(p -> StaffBuilder.staff()
                .withUserProfile(p)
                .withFullName(body.getFullName())
                .withAddress(body.getAddress())
                .withPhoneNumbers(body.getPhoneNumbers())
                .build());

        return labStaff
                .map(staffMapper::asLabStaff)
                .map(labStaffDao::save);
    }

    @Override
    public Optional<LabStaff> update(LabStaff body, Long id) {
        return getBuild(body, id);
    }

    @Override
    public void delete(Long id) {
        labStaffDao.deleteById(id);
    }

    private Optional<LabStaff> getBuild(Staff staff, Long id) {
        UserProfile profileToAdd = staff.getUserProfile();

//        userProfileService.delete(profileToAdd.getId());

        Optional<Staff> labStaff = userProfileService.updateProfile(profileToAdd, id)
                .map(profile -> StaffBuilder.staff()
                        .withUserProfile(profile)
                        .withFullName(staff.getFullName())
                        .withAddress(staff.getAddress())
                        .withPhoneNumbers(staff.getPhoneNumbers())
                        .withId(id)
                        .build());

        return labStaff
                .map(staffMapper::asLabStaff)
                .map(labStaffDao::save);
    }
}
