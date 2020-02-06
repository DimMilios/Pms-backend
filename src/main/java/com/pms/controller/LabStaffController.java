package com.pms.controller;

import com.pms.dao.LabStaffDao;
import com.pms.model.staff.LabStaff;
import com.pms.model.staff.Staff;
import com.pms.service.LabStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Lab staff controller.
 */
@RestController
@RequestMapping("api/lab-staff")
@CrossOrigin
public class LabStaffController {

    private final LabStaffService labStaffService;

    /**
     * Instantiates a new Lab staff controller.
     *
     * @param labStaffService the lab staff service
     */
    @Autowired
    public LabStaffController(LabStaffService labStaffService) {
        this.labStaffService = labStaffService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Iterable<LabStaff> getAll() {
        return labStaffService.getAll();
    }

    /**
     * Gets by id.
     *
     * @param staffId the staff id
     * @return the by id
     */
    @GetMapping(path = "{staffId}")
    public LabStaff getById(@PathVariable Long staffId) {
        return labStaffService.getById(staffId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Not found"));
    }

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    @GetMapping(path = "username/{username}")
    public LabStaff getByUsername(@PathVariable String username) {
        return labStaffService.getByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Staff not found"));
    }

    /**
     * Create lab staff.
     *
     * @param labStaff the lab staff
     * @return the lab staff
     */
    @PostMapping
    public LabStaff create(@RequestBody LabStaff labStaff) {
        return labStaffService.create(labStaff)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Error creating Lab Staff"));
    }

    /**
     * Update lab staff.
     *
     * @param staffToUpdate the staff to update
     * @param staffId       the staff id
     * @return the lab staff
     */
    @PutMapping(path = "{staffId}")
    public LabStaff update(
            @RequestBody LabStaff staffToUpdate, @PathVariable Long staffId) {
        return labStaffService.update(staffToUpdate, staffId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error updating Lab Staff"));
    }

    /**
     * Delete response entity.
     *
     * @param staffId the staff id
     * @return the response entity
     */
    @DeleteMapping(path = "{staffId}")
    public ResponseEntity<?> delete(@PathVariable Long staffId) {
        labStaffService.delete(staffId);
        return ResponseEntity.ok()
                .body("Lab staff with id: " + staffId + " deleted");
    }
}
