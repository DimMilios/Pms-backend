package com.pms.controller;

import com.pms.model.admin.Admin;
import com.pms.model.userprofile.UserProfile;
import com.pms.service.AdminService;
import com.pms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

/**
 * The type Admin controller.
 */
@RestController
@RequestMapping("api/admin")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;
    private final UserProfileService userProfileService;

    /**
     * Instantiates a new Admin controller.
     *
     * @param adminService       the admin service
     * @param userProfileService the user profile service
     */
    @Autowired
    public AdminController(AdminService adminService, UserProfileService userProfileService) {
        this.adminService = adminService;
        this.userProfileService = userProfileService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public Iterable<Admin> getAll() {
        return adminService.getAll();
    }

    /**
     * Gets by id.
     *
     * @param adminId the admin id
     * @return the by id
     */
    @GetMapping(path = "{adminId}")
    public Admin getById(@PathVariable("adminId") Long adminId) {
        return adminService.getById(adminId)
                .orElseThrow(() ->
                        new RuntimeException("Could not find admin with id: " + adminId));
    }

    /**
     * Create admin admin.
     *
     * @param adminToAdd the admin to add
     * @return the admin
     */
    @PostMapping
    public Admin createAdmin(@Valid @RequestBody Admin adminToAdd) {
        return adminService.create(adminToAdd)
                .orElseThrow(() -> new RuntimeException("Error creating admin"));
    }

    /**
     * Update admin admin.
     *
     * @param adminId  the admin id
     * @param newAdmin the new admin
     * @return the admin
     */
    @PutMapping(path = "{adminId}")
    public Admin updateAdmin(@PathVariable("adminId") Long adminId,
                             @Valid @RequestBody Admin newAdmin) {

        return adminService.update(newAdmin, adminId)
        .orElseThrow(() ->
                new RuntimeException("Could not update admin with id: " + adminId));
    }

    /**
     * Delete admin response entity.
     *
     * @param adminId the admin id
     * @return the response entity
     */
    @DeleteMapping(path = "{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        try {
            adminService.delete(adminId);
            return ResponseEntity.ok()
                        .body("Admin with id: " + adminId + " deleted");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not find admin with id: " + adminId);
        }
    }

    /**
     * Gets admin profile.
     *
     * @param adminId the admin id
     * @return the admin profile
     */
    @GetMapping(path = "{adminId}/user-profile")
    public UserProfile getAdminProfile(@PathVariable Long adminId) {
        return adminService
                .getById(adminId)
                .map(Admin::getUserProfile)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "error creating profile"));
    }

    /**
     * Create admin profile admin.
     *
     * @param adminId   the admin id
     * @param profileId the profile id
     * @return the admin
     */
    @PutMapping(path = "{adminId}/user-profiles/{profileId}")
    public Admin createAdminProfile(@PathVariable Long adminId, @PathVariable Long profileId) {
        UserProfile profile = userProfileService.getById(profileId).get();

        Admin admin = adminService.getById(adminId).get();
        admin.setUserProfile(profile);
        return adminService.update(admin, adminId).get();
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "error creating profile"));
    }

    /**
     * Delete profile response entity.
     *
     * @param adminId the admin id
     * @return the response entity
     */
    @DeleteMapping(path = "{adminId}/user-profiles")
    public ResponseEntity<?> deleteProfile(@PathVariable Long adminId) {
        Admin admin =  adminService.getById(adminId).get();
        admin.setUserProfile(null);
        adminService.update(admin, adminId);
        return ResponseEntity.ok()
                .body("User profile for admin with id: " + adminId + " deleted");
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "error creating profile"));
    }
}
