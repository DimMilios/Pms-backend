package com.pms.controller;

import com.pms.dao.AdminDao;
import com.pms.dao.UserProfileDao;
import com.pms.model.admin.Admin;
import com.pms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/admin")
@CrossOrigin
public class AdminController {

    private UserProfileDao userProfileDao;
    private AdminDao adminDao;
    private AdminService adminService;

    @Autowired
    public AdminController(UserProfileDao userProfileDao, AdminDao adminDao, AdminService adminService) {
        this.userProfileDao = userProfileDao;
        this.adminDao = adminDao;
        this.adminService = adminService;
    }

    @GetMapping
    public Iterable<Admin> getAll() {
        return adminService.getAll();
    }

    @GetMapping(path = "{adminId}")
    public Admin getById(@PathVariable("adminId") Long adminId) {
        return adminService.getById(adminId)
                .orElseThrow(() ->
                        new RuntimeException("Could not find admin with id: " + adminId));
    }

    @PostMapping
    public Admin createAdmin(@Valid @RequestBody Admin adminToAdd) {
        return adminService.create(adminToAdd)
                .orElseThrow(() -> new RuntimeException("Error creating admin"));
    }

    @PutMapping(path = "{adminId}")
    public Admin updateAdmin(@PathVariable("adminId") Long adminId,
                             @Valid @RequestBody Admin newAdmin) {

        return adminService.update(newAdmin, adminId)
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
