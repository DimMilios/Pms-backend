package com.pms.controller;

import com.pms.dao.StaffDao;
import com.pms.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class StaffController {

    private StaffDao staffDao;

    @Autowired
    public StaffController(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    @GetMapping(path = "staff")
    public Iterable<Staff> getAll() {
        return staffDao.findAll();
    }

    @GetMapping(path = "staff/{staffId}")
    public Staff getById(@PathVariable("staffId") Long staffId) {
        return staffDao.findById(staffId)
                .orElseThrow(() ->
                        new RuntimeException("Could not find staff member with id: " + staffId));
    }



}
