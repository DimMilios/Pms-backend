package com.pms.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pms.model.userprofile.UserProfile;
import com.pms.service.UserProfileService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * The type User profile controller.
 */
@RestController
@RequestMapping(path = "api/user-profiles")
@CrossOrigin
public class UserProfileController {

    private UserProfileService userProfileService;

    /**
     * Instantiates a new User profile controller.
     *
     * @param userProfileService the user profile service
     */
    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Iterable<UserProfile> getAll() {
        return userProfileService.getAll();
    }

    /**
     * Gets by id.
     *
     * @param userProfileId the user profile id
     * @return the by id
     */
    @GetMapping(path = "{userProfileId}")
    public UserProfile getById(@PathVariable("userProfileId") Long userProfileId) {
        return userProfileService.getById(userProfileId)
                .orElseThrow(() -> new RuntimeException("User with this userProfileId doesn't exist"));
    }

    /**
     * Create profile user profile.
     *
     * @param profileToCreate the profile to create
     * @return the user profile
     */
    @PostMapping
    public UserProfile createProfile(@Valid @RequestBody UserProfile profileToCreate) {
        try {
            return userProfileService.createProfile(profileToCreate)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "error creating profile"));
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    /**
     * Update profile user profile.
     *
     * @param userProfileId  the user profile id
     * @param newUserProfile the new user profile
     * @return the user profile
     */
    @PutMapping(path = "{userProfileId}")
    public UserProfile updateProfile(@PathVariable("userProfileId") Long userProfileId,
                                     @Valid @RequestBody UserProfile newUserProfile) {
        return userProfileService.updateProfile(newUserProfile, userProfileId)
                .orElseThrow(() -> new RuntimeException("error updating profile"));
    }


    /**
     * Delete profile response entity.
     *
     * @param userProfileId the user profile id
     * @return the response entity
     */
    @DeleteMapping(path = "{userProfileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable("userProfileId") Long userProfileId) {
        return userProfileService.delete(userProfileId);
    }
}
