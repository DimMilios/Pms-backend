package com.pms.controller;

import com.pms.dao.AdminDao;
import com.pms.dao.UserProfileDao;
import com.pms.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/admin")
@CrossOrigin
public class AdminController {

    private UserProfileDao userProfileDao;
    private AdminDao adminDao;

    @Autowired
    public AdminController(UserProfileDao userProfileDao, AdminDao adminDao) {
        this.userProfileDao = userProfileDao;
        this.adminDao = adminDao;
    }

    @GetMapping
    public Iterable<Admin> getAll() {
        return adminDao.findAll();
    }

    @GetMapping(path = "{adminId}")
    public Admin getById(@PathVariable("adminId") Long adminId) {
        return adminDao.findById(adminId)
                .orElseThrow(() ->
                        new RuntimeException("Could not find admin with id: " + adminId));
    }

    @PostMapping
    public Admin createAdmin(@Valid @RequestBody Admin adminToAdd) {
        return adminDao.save(adminToAdd);
    }

    @PutMapping(path = "{adminId}")
    public Admin updateAdmin(@PathVariable("adminId") Long adminId,
                             @Valid @RequestBody Admin newAdmin) {

        Optional<Admin> adminToChange = adminDao.findById(adminId);

        return adminToChange.map(admin -> {
            admin.setUserProfile(newAdmin.getUserProfile());
            return adminDao.save(admin);
        })
        .orElseThrow(() ->
                new RuntimeException("Could not update admin with id: " + adminId));
    }

    @DeleteMapping(path = "{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        return adminDao.findById(adminId)
                .map(admin -> {
                    adminDao.delete(admin);
                    return ResponseEntity.ok()
                            .body("Admin with id: " + adminId + " deleted");
                })
                .orElseThrow(() ->
                        new RuntimeException("Could not find admin with id: " + adminId));
    }
}
