package com.pms.service;

import com.pms.dao.UserProfileDao;
import com.pms.model.userprofile.UserProfile;
import com.pms.model.userprofile.UserProfileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    private UserProfileDao userProfileDao;

    @Autowired
    public UserProfileService(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    public Iterable<UserProfile> getAll() {
        return userProfileDao.findAll();
    }

    public Optional<UserProfile> getById(Long id) {
        return userProfileDao.findById(id);
    }

    public Optional<UserProfile> createProfile(UserProfile userProfile) {
        UserProfile profileToAdd = getBuild(userProfile, userProfile.getId());

        return Optional.of(userProfileDao.save(profileToAdd));
    }

    public Optional<UserProfile> updateProfile(UserProfile userProfile, Long id) {
        UserProfile profileToAdd = getBuild(userProfile, id);

        return Optional.of(userProfileDao.save(profileToAdd));
    }

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
