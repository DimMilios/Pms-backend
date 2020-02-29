package com.pms.controller;

import com.pms.model.userprofile.UserProfile;
import com.pms.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/user-profiles")
@CrossOrigin
public class UserProfileController {

    private final Logger log = LoggerFactory.getLogger(UserProfileController.class);
    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN')")
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
        try {
            log.info("Request to create profile: {}", profileToCreate);
            return userProfileService.createProfile(profileToCreate)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "error creating profile"));
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping(path = "{userProfileId}")
    public UserProfile updateProfile(@PathVariable("userProfileId") Long userProfileId,
                                     @Valid @RequestBody UserProfile newUserProfile) {
        log.info("Request to update profile: {}", newUserProfile);
        return userProfileService.updateProfile(newUserProfile, userProfileId)
                .orElseThrow(() -> new RuntimeException("error updating profile"));
    }


    @DeleteMapping(path = "{userProfileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable("userProfileId") Long userProfileId) {
        return userProfileService.delete(userProfileId);
    }
}
