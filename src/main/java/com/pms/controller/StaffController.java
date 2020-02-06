package com.pms.controller;

import com.pms.dao.StaffDao;
import com.pms.model.patient.Patient;
import com.pms.model.staff.Staff;
import com.pms.model.userprofile.UserProfile;
import com.pms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * The type Staff controller.
 */
@RestController
@RequestMapping("api/staff")
@CrossOrigin
public class StaffController {

    private StaffDao staffDao;
    private final StaffService staffService;

    /**
     * Instantiates a new Staff controller.
     *
     * @param staffDao     the staff dao
     * @param staffService the staff service
     */
    @Autowired
    public StaffController(StaffDao staffDao, StaffService staffService) {
        this.staffDao = staffDao;
        this.staffService = staffService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Iterable<Staff> getAll() {
        return staffDao.findAll();
    }

    /**
     * Gets by id.
     *
     * @param staffId the staff id
     * @return the by id
     */
    @GetMapping(path = "{staffId}")
    public Staff getById(@PathVariable("staffId") Long staffId) {
        return staffDao.findById(staffId)
                .orElseThrow(() ->
                        new RuntimeException("Could not find staff member with id: " + staffId));
    }

    /**
     * Gets profile by id.
     *
     * @param staffId the staff id
     * @return the profile by id
     */
    @GetMapping(path = "{staffId}/user-profiles")
    public UserProfile getProfileById(@PathVariable Long staffId) {
        return staffDao.findById(staffId).map(Staff::getUserProfile)
                .orElseThrow(() ->
                        new RuntimeException("Could not find staff member with id: " + staffId));
    }

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    @GetMapping(path = "username/{username}")
    public Staff getByUsername(@PathVariable String username) {
        return staffService.getByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Staff not found"));
    }

    /**
     * Create staff.
     *
     * @param staffToCreate the staff to create
     * @return the staff
     */
    @PostMapping
    public Staff create(@Valid @RequestBody Staff staffToCreate) {
//        return staffDao.save(staffToCreate);
        return staffService.create(staffToCreate)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "error creating staff"));
    }

    /**
     * Update staff.
     *
     * @param staffToUpdate the staff to update
     * @param staffId       the staff id
     * @return the staff
     */
    @PutMapping(path = "{staffId}")
    public Staff update(@Valid @RequestBody Staff staffToUpdate, @PathVariable Long staffId) {
//        return staffDao.save(staffToUpdate);
        return staffService.update(staffToUpdate, staffId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "error updating staff"));
    }

    /**
     * Delete response entity.
     *
     * @param staffId the staff id
     * @return the response entity
     */
    @DeleteMapping(path = "{staffId}")
    public ResponseEntity<?> delete(@PathVariable Long staffId) {
        return staffDao.findById(staffId)
                .map(staff -> {
                    staffDao.delete(staff);
                    return ResponseEntity.ok()
                            .body("Staff member with id: " + staffId + " deleted");
                }).orElseThrow(() ->
                        new RuntimeException("Could not find staff member with id: " + staffId));
    }

}
