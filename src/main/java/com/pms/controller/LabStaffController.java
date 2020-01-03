package com.pms.controller;

import com.pms.dao.LabStaffDao;
import com.pms.model.LabStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lab-staff")
@CrossOrigin
public class LabStaffController {

    private LabStaffDao labStaffDao;

    @Autowired
    public LabStaffController(LabStaffDao labStaffDao) {
        this.labStaffDao = labStaffDao;
    }

    @GetMapping
    public Iterable<LabStaff> getAll() {
        return labStaffDao.findAll();
    }
}
