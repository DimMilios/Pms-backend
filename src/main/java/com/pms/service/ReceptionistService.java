package com.pms.service;

import com.pms.dao.ReceptionistDao;
import com.pms.model.staff.*;
import com.pms.model.userprofile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceptionistService implements GenericService<Receptionist> {

    private final ReceptionistDao receptionistDao;
    private final UserProfileService userProfileService;
    private final StaffMapper staffMapper;

    @Autowired
    public ReceptionistService(ReceptionistDao receptionistDao,
                               UserProfileService userProfileService,
                               StaffMapper staffMapper) {
        this.receptionistDao = receptionistDao;
        this.userProfileService = userProfileService;
        this.staffMapper = staffMapper;
    }

    @Override
    public Iterable<Receptionist> getAll() {
        return receptionistDao.findAll();
    }

    @Override
    public Optional<Receptionist> getById(Long id) {
        return receptionistDao.findById(id);
    }

    @Override
    public Optional<Receptionist> create(Receptionist body) {
        return getBuild(body, body.getId());
    }

    @Override
    public Optional<Receptionist> update(Receptionist body, Long id) {
        return getBuild(body, id);
    }

    @Override
    public void delete(Long id) {
        receptionistDao.deleteById(id);
    }

    private Optional<Receptionist> getBuild(Staff staff, Long id) {
        UserProfile profileToAdd = staff.getUserProfile();

        Optional<Staff> receptionist = userProfileService.createProfile(profileToAdd)
                .map(profile -> StaffBuilder.staff()
                        .withUserProfile(profile)
                        .withFullName(staff.getFullName())
                        .withAddress(staff.getAddress())
                        .withPhoneNumbers(staff.getPhoneNumbers())
                        .withId(id)
                        .build());

        return receptionist
                .map(staffMapper::asReceptionist)
                .map(receptionistDao::save);
    }
}
