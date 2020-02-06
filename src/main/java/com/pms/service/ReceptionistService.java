package com.pms.service;

import com.pms.dao.ReceptionistDao;
import com.pms.model.staff.*;
import com.pms.model.userprofile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Receptionist service.
 */
@Service
public class ReceptionistService implements GenericService<Receptionist> {

    private final ReceptionistDao receptionistDao;
    private final UserProfileService userProfileService;
    private final StaffMapper staffMapper;

    /**
     * Instantiates a new Receptionist service.
     *
     * @param receptionistDao    the receptionist dao
     * @param userProfileService the user profile service
     * @param staffMapper        the staff mapper
     */
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

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    public Optional<Receptionist> getByUsername(String username) {
        return receptionistDao.findByUserProfileUsername(username);
    }


    @Override
    public Optional<Receptionist> create(Receptionist body) {
        return createBuild(body);
    }

    @Override
    public Optional<Receptionist> update(Receptionist body, Long id) {
        return getBuild(body, id);
    }

    @Override
    public void delete(Long id) {
        receptionistDao.deleteById(id);
    }

    private Optional<Receptionist> createBuild(Receptionist receptionist) {
        UserProfile profileToAdd = receptionist.getUserProfile();

        Optional<Staff> staff = userProfileService.createProfile(profileToAdd)
                .map(profile -> StaffBuilder.staff()
                        .withUserProfile(profile)
                        .withFullName(receptionist.getFullName())
                        .withAddress(receptionist.getAddress())
                        .withPhoneNumbers(receptionist.getPhoneNumbers())
                        .build());

        return staff
                .map(staffMapper::asReceptionist)
                .map(receptionistDao::save);
    }

    private Optional<Receptionist> getBuild(Staff staff, Long id) {
        UserProfile profileToAdd = staff.getUserProfile();
        Optional<UserProfile> profile = userProfileService.updateProfile(profileToAdd, profileToAdd.getId());


        Optional<Receptionist> receptionist = receptionistDao.findById(id);

        return receptionist.map(rec -> {
            rec.setUserProfile(profile.get());
            rec.setFullName(staff.getFullName());
            rec.setAddress(staff.getAddress());
            rec.setPhoneNumbers(staff.getPhoneNumbers());
            return rec;
        }).map(receptionistDao::save);
    }
}
