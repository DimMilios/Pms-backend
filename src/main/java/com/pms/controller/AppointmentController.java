package com.pms.controller;

import com.pms.controller.exceptions.MyResourceNotFoundException;
import com.pms.dao.AppointmentDao;
import com.pms.dao.PatientDao;
import com.pms.dao.StaffDao;
import com.pms.model.appointment.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/appointments")
@CrossOrigin
public class AppointmentController {

    private AppointmentDao appointmentDao;
    private StaffDao staffDao;
    private PatientDao patientDao;

    private AppointmentService appointmentService;

    Logger logger = LogManager.getLogger(AppointmentController.class);

    @Autowired
    public AppointmentController(AppointmentDao appointmentDao, StaffDao staffDao, PatientDao patientDao, AppointmentService appointmentService) {
        this.appointmentDao = appointmentDao;
        this.staffDao = staffDao;
        this.patientDao = patientDao;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public Iterable<Appointment> getAll() {
        return appointmentDao.findAll();
    }

    @GetMapping(path = "{doctorId}/{ssn}")
    public Appointment getOne(@PathVariable("doctorId") Long doctorId,
                              @PathVariable("ssn") Long ssn) {
        return appointmentService.selectByDoctorIdAndSsn(doctorId, ssn)
                .orElseThrow(() -> new MyResourceNotFoundException("Could not find appointment"));
    }

//    @PostMapping
//    public Appointment create(@Valid @RequestParam Doctor doctor,
//                              @Valid @RequestParam Patient patient) {
//        return appointmentService.createAppointment(doctor, patient)
//                .orElseThrow(() -> new RuntimeException("Creation failed"));
//    }

    @PostMapping
    public @ResponseBody Appointment create(@RequestParam Long doctorId, @RequestParam Long ssn) {
        Optional<Appointment> appointment =  appointmentService.createAppointment(doctorId, ssn);
        return appointment.map(app -> appointmentDao.save(app))
                .orElseThrow(() -> new RuntimeException("Error saving appointment"));
    }
}
//