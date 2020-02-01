package com.pms.controller;

import com.pms.dao.StaffDao;
import com.pms.model.staff.Staff;
import com.pms.model.userprofile.UserProfile;
import com.pms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/staff")
@CrossOrigin
public class StaffController {

    private StaffDao staffDao;
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffDao staffDao, StaffService staffService) {
        this.staffDao = staffDao;
        this.staffService = staffService;
    }

    @GetMapping
    public Iterable<Staff> getAll() {
        return staffDao.findAll();
    }

    @GetMapping(path = "{staffId}")
    public Staff getById(@PathVariable("staffId") Long staffId) {
        return staffDao.findById(staffId)
                .orElseThrow(() ->
                        new RuntimeException("Could not find staff member with id: " + staffId));
    }

    @GetMapping(path = "{staffId}/user-profiles")
    public UserProfile getProfileById(@PathVariable Long staffId) {
        return staffDao.findById(staffId).map(Staff::getUserProfile)
                .orElseThrow(() ->
                        new RuntimeException("Could not find staff member with id: " + staffId));
    }

    @PostMapping
    public Staff create(@Valid @RequestBody Staff staffToCreate) {
//        return staffDao.save(staffToCreate);
        return staffService.create(staffToCreate)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "error creating staff"));
    }

    @PutMapping(path = "{staffId}")
    public Staff update(@Valid @RequestBody Staff staffToUpdate, @PathVariable Long staffId) {
//        return staffDao.save(staffToUpdate);
        return staffService.update(staffToUpdate, staffId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "error updating staff"));
    }

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
