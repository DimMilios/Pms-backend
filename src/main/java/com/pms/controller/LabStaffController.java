package com.pms.controller;

import com.pms.dao.LabStaffDao;
import com.pms.model.staff.LabStaff;
import com.pms.service.LabStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/lab-staff")
@CrossOrigin
public class LabStaffController {

    private final LabStaffService labStaffService;

    @Autowired
    public LabStaffController(LabStaffService labStaffService) {
        this.labStaffService = labStaffService;
    }

    @GetMapping
    public Iterable<LabStaff> getAll() {
        return labStaffService.getAll();
    }

    @PostMapping
    public LabStaff create(@RequestBody LabStaff labStaff) {
        return labStaffService.create(labStaff)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Error creating Lab Staff"));
    }

    @PutMapping(path = "{staffId}")
    public LabStaff update(
            @RequestBody LabStaff staffToUpdate, @PathVariable Long staffId) {
        return labStaffService.update(staffToUpdate, staffId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error updating Lab Staff"));
    }

    @DeleteMapping(path = "{staffId}")
    public ResponseEntity<?> delete(@PathVariable Long staffId) {
        labStaffService.delete(staffId);
        return ResponseEntity.ok()
                .body("Lab staff with id: " + staffId + " deleted");
    }
}
