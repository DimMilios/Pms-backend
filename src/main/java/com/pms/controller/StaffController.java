package com.pms.controller;

import com.pms.dao.StaffDao;
import com.pms.dao.UserProfileDao;
import com.pms.model.Staff;
import com.pms.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/staff")
@CrossOrigin
public class StaffController {

    private StaffDao staffDao;
    private UserProfileDao userProfileDao;

    @Autowired
    public StaffController(StaffDao staffDao, UserProfileDao userProfileDao) {
        this.staffDao = staffDao;
        this.userProfileDao = userProfileDao;
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
        return staffDao.save(staffToCreate);
    }

    @PutMapping(path = "{staffId}")
    public Staff update(@Valid @RequestBody Staff staffToUpdate, @PathVariable Long staffId) {
        return staffDao.save(staffToUpdate);
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
