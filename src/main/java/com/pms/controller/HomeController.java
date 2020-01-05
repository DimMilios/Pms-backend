package com.pms.controller;

import com.pms.dao.UserProfileDao;
import com.pms.model.Patient;
import com.pms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    private PatientService patientService;
    private UserProfileDao userProfileDao;

    @Autowired
    public HomeController(PatientService patientService, UserProfileDao userProfileDao) {
        this.patientService = patientService;
        this.userProfileDao = userProfileDao;
    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);

        return "index";
    }

    @RequestMapping("/delete/{ssn}")
    public String deleteProduct(@PathVariable(name = "ssn") Long ssn) {
        patientService.deleteBySsn(ssn);
        return "redirect:/";
    }
}
