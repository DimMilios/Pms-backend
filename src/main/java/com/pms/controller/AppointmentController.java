package com.pms.controller;

import com.pms.dao.AppointmentDao;
import com.pms.model.appointment.Appointment;
import com.pms.model.staff.Doctor;
import com.pms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type Appointment controller.
 */
@RestController
@RequestMapping("api/appointments")
@CrossOrigin
public class AppointmentController {

    private AppointmentDao appointmentDao;

    private AppointmentService appointmentService;


    /**
     * Instantiates a new Appointment controller.
     *
     * @param appointmentDao     the appointment dao
     * @param appointmentService the appointment service
     */
    @Autowired
    public AppointmentController(AppointmentDao appointmentDao,
                                 AppointmentService appointmentService) {
        this.appointmentDao = appointmentDao;
        this.appointmentService = appointmentService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public Iterable<Appointment> getAll() {
        return appointmentDao.findAll();
    }

    /**
     * Gets one.
     *
     * @param doctorId the doctor id
     * @param ssn      the ssn
     * @return the one
     */
    @GetMapping(path = "{doctorId}/{ssn}")
    public Appointment getOne(@PathVariable("doctorId") Long doctorId,
                              @PathVariable("ssn") Long ssn) {
        return appointmentService.selectByDoctorIdAndSsn(doctorId, ssn)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Appointment not found"));
    }

    /**
     * Gets one by doctor id.
     *
     * @param doctorId the doctor id
     * @return the one by doctor id
     */
    @GetMapping(path = "doctor/{doctorId}")
    public Appointment getOneByDoctorId(@PathVariable(value = "doctorId") Long doctorId) {
        return appointmentService.selectByDoctorId(doctorId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Appointment not found"));
    }

    /**
     * Gets one by ssn.
     *
     * @param ssn the ssn
     * @return the one by ssn
     */
    @GetMapping(path = "patient/{ssn}")
    public Appointment getOneBySsn(@PathVariable(value = "ssn") Long ssn) {
        return appointmentService.selectByPatientSsn(ssn)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Appointment not found"));
    }

    /**
     * Gets by different date and time.
     *
     * @param date the date
     * @param time the time
     * @return the by different date and time
     */
    @GetMapping(path = "doctor-availability")
    public Doctor getByDifferentDateAndTime(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time
    ) {
        return appointmentService.selectNotDateAndTime(date, time)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Doctor not available at that date and time"));
    }

    @GetMapping(path = "check-doctor")
    public Doctor getByDifferentDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return appointmentService.selectNotDate(date)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Doctor not available at that date and time"));
    }

//    @PostMapping
//    public Appointment create(@Valid @RequestParam Doctor doctor,
//                              @Valid @RequestParam Patient patient) {
//        return appointmentService.createAppointment(doctor, patient)
//                .orElseThrow(() -> new RuntimeException("Creation failed"));
//    }

    /**
     * Create appointment.
     *
     * @param doctorId the doctor id
     * @param ssn      the ssn
     * @param date     the date
     * @param time     the time
     * @return the appointment
     */
    @PostMapping
    public Appointment create(@RequestParam Long doctorId,
                              @RequestParam Long ssn,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time) {
        try {
            return appointmentService.createAppointment(doctorId, ssn, date, time)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Error creating appointment"));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Appointment date and time not available");
        }

    }

    /**
     * Delete by id response entity.
     *
     * @param doctorId the doctor id
     * @return the response entity
     */
    @DeleteMapping(path = "doctor/{doctorId}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "doctorId") Long doctorId) {
        appointmentService.deleteById(doctorId);
        return ResponseEntity.ok()
                .body("Deleted appointments for doctor with id: " + doctorId);
    }

    /**
     * Delete by ssn response entity.
     *
     * @param ssn the ssn
     * @return the response entity
     */
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

    /**
     * Delete by date response entity.
     *
     * @param date the date
     * @param time the time
     * @return the response entity
     */
    @DeleteMapping("/date-time")
    public ResponseEntity<?> deleteByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time) {
        appointmentService.deleteByDateAndTime(date, time);
        return ResponseEntity.ok()
                .body("Deleted appointment at: " + date.toString() + " " + time.toString());
    }
}
