package com.pms.service;

import com.pms.dao.UserProfileDao;
import com.pms.model.Role;
import com.pms.model.userprofile.UserProfile;
import com.pms.model.userprofile.UserProfileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * The type User profile service.
 */
@Service
@Transactional
public class UserProfileService {

    private UserProfileDao userProfileDao;

    /**
     * Instantiates a new User profile service.
     *
     * @param userProfileDao the user profile dao
     */
    @Autowired
    public UserProfileService(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public Iterable<UserProfile> getAll() {
        return userProfileDao.findAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<UserProfile> getById(Long id) {
        return userProfileDao.findById(id);
    }

    /**
     * Create profile optional.
     *
     * @param userProfile the user profile
     * @return the optional
     */
    public Optional<UserProfile> createProfile(UserProfile userProfile) {
        UserProfile profileToAdd = UserProfileBuilder
                .userProfile()
                .withUsername(userProfile.getUsername())
                .withPassword(userProfile.getPassword())
                .withEmail(userProfile.getEmail())
                .withRole(userProfile.getRole().toString())
                .build();

        return Optional.of(userProfileDao.save(profileToAdd));
    }

    /**
     * Update profile optional.
     *
     * @param userProfile the user profile
     * @param id          the id
     * @return the optional
     */
    public Optional<UserProfile> updateProfile(UserProfile userProfile, Long id) {
        UserProfile profileToAdd = getBuild(userProfile, id);

        return Optional.of(userProfileDao.save(profileToAdd));
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    public ResponseEntity<?> delete(Long id) {
        return userProfileDao.findById(id)
                .map(userProfile -> {
                    userProfileDao.delete(userProfile);
                    return ResponseEntity.ok()
                            .body("User profile with id: " + id + " deleted");
                }).orElseThrow(() ->
                        new RuntimeException("Could not find user profile with id: " + id));
    }

    private UserProfile getBuild(UserProfile userProfile, Long id) {
        return UserProfileBuilder
                .userProfile()
                .withUsername(userProfile.getUsername())
                .withPassword(userProfile.getPassword())
                .withEmail(userProfile.getEmail())
                .withRole(userProfile.getRole().toString())
                .withId(id)
                .build();
    }
}
