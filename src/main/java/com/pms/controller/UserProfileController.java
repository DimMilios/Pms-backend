package com.pms.controller;

import com.pms.dao.UserProfileDao;
import com.pms.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/user-profiles")
@CrossOrigin
public class UserProfileController {

    private UserProfileDao userProfileDao;

    @Autowired
    public UserProfileController(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    @GetMapping
    public Iterable<UserProfile> getAll() {
        return userProfileDao.findAll();
    }

    @GetMapping(path = "{userProfileId}")
    public UserProfile getById(@PathVariable("userProfileId") Long userProfileId) {
        return userProfileDao.findById(userProfileId)
                .orElseThrow(() -> new RuntimeException("User with this userProfileId doesn't exist"));
    }

    @PostMapping
    public UserProfile createProfile(@Valid @RequestBody UserProfile profileToCreate) {
        return userProfileDao.save(profileToCreate);
    }

    @PutMapping(path = "{userProfileId}")
    public UserProfile updateProfile(@PathVariable("userProfileId") Long userProfileId,
                                     @Valid @RequestBody UserProfile newUserProfile) {
        Optional<UserProfile> userProfileToChange =  userProfileDao.findById(userProfileId);
        return userProfileToChange.map(profile -> {
            profile.setUsername(newUserProfile.getUsername());
            profile.setPassword(newUserProfile.getPassword());
            profile.setEmail(newUserProfile.getEmail());
            profile.setRole(newUserProfile.getRole());
            return userProfileDao.save(profile);
        })
        .orElseThrow(() ->
                new RuntimeException("Could not find user profile with id: " + userProfileId));
    }


    @DeleteMapping(path = "{userProfileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable("userProfileId") Long userProfileId) {
        return userProfileDao.findById(userProfileId)
                .map(userProfile -> {
                    userProfileDao.delete(userProfile);
                    return ResponseEntity.ok()
                            .body("User profile with id: " + userProfileId + " deleted");
                }).orElseThrow(() ->
                        new RuntimeException("Could not find user profile with id: " + userProfileId));
    }
}
