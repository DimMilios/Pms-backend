package com.pms.controller;

import com.pms.model.UserProfile;
import com.pms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/user-profiles")
@CrossOrigin
public class UserProfileController {

    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public Iterable<UserProfile> getAll() {
        return userProfileService.getAll();
    }

    @GetMapping(path = "{userProfileId}")
    public UserProfile getById(@PathVariable("userProfileId") Long userProfileId) {
        return userProfileService.getById(userProfileId)
                .orElseThrow(() -> new RuntimeException("User with this userProfileId doesn't exist"));
    }

    @PostMapping
    public UserProfile createProfile(@Valid @RequestBody UserProfile profileToCreate) {
        return userProfileService.createProfile(profileToCreate)
                .orElseThrow(() -> new RuntimeException("error creating profile"));
    }

    @PutMapping(path = "{userProfileId}")
    public UserProfile updateProfile(@PathVariable("userProfileId") Long userProfileId,
                                     @Valid @RequestBody UserProfile newUserProfile) {
        return userProfileService.updateProfile(newUserProfile, userProfileId)
                .orElseThrow(() -> new RuntimeException("error updating profile"));
    }


    @DeleteMapping(path = "{userProfileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable("userProfileId") Long userProfileId) {
        return userProfileService.delete(userProfileId);
    }
}
