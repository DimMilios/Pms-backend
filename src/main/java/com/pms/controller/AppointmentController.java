package com.pms.controller;

import com.pms.controller.exceptions.MyResourceNotFoundException;
import com.pms.dao.AppointmentDao;
import com.pms.dao.PatientDao;
import com.pms.dao.StaffDao;
import com.pms.model.appointment.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("api/appointments")
@CrossOrigin
public class AppointmentController {

    private AppointmentDao appointmentDao;

    private AppointmentService appointmentService;

    Logger logger = LogManager.getLogger(AppointmentController.class);

    @Autowired
    public AppointmentController(AppointmentDao appointmentDao, AppointmentService appointmentService) {
        this.appointmentDao = appointmentDao;
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
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Appointment not found"));
    }

    @GetMapping(path = "doctor/{doctorId}")
    public Appointment getOneByDoctorId(@PathVariable(value = "doctorId") Long doctorId) {
        return appointmentService.selectByDoctorId(doctorId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Appointment not found"));
    }

    @GetMapping(path = "patient/{ssn}")
    public Appointment getOneBySsn(@PathVariable(value = "ssn") Long ssn) {
        return appointmentService.selectByPatientSsn(ssn)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Appointment not found"));
    }

//    @PostMapping
//    public Appointment create(@Valid @RequestParam Doctor doctor,
//                              @Valid @RequestParam Patient patient) {
//        return appointmentService.createAppointment(doctor, patient)
//                .orElseThrow(() -> new RuntimeException("Creation failed"));
//    }

    @PostMapping
    public Appointment create(@RequestParam Long doctorId,
                              @RequestParam Long ssn,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time) {
        return appointmentService.createAppointment(doctorId, ssn, date, time)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error creating appointment"));
    }

    @DeleteMapping(path = "doctor/{doctorId}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "doctorId") Long doctorId) {
        appointmentService.deleteById(doctorId);
        return ResponseEntity.ok()
                .body("Deleted appointments for doctor with id: " + doctorId);
    }

    @DeleteMapping(path = "patient/{ssn}")
    public ResponseEntity<?> deleteBySsn(@PathVariable(value = "ssn") Long ssn) {
        appointmentService.deleteBySsn(ssn);
        return ResponseEntity.ok()
                .body("Deleted appointments for patient with ssn: " + ssn);
    }

//    @DeleteMapping
//    public ResponseEntity<?> deleteByDate(@RequestBody Timestamp timestamp) {
//        appointmentService.deleteByAppointmentDate(timestamp);
//
//        return ResponseEntity.ok()
//                .body("Deleted appointment");
//    }

    @DeleteMapping("/date-time")
    public ResponseEntity<?> deleteByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time) {
        appointmentService.deleteByDateAndTime(date, time);
        return ResponseEntity.ok()
                .body("Deleted appointment at: " + date.toString() + " " + time.toString());
    }
}
