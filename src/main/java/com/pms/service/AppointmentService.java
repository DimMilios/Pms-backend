//package com.pms.service;
//
//import com.pms.dao.AppointmentDao;
//import com.pms.model.appointment.Appointment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AppointmentService {
//
//    private final AppointmentDao appointmentDao;
//
//    @Autowired
//    public AppointmentService(AppointmentDao appointmentDao) {
//        this.appointmentDao = appointmentDao;
//    }
//
//    public Iterable<Appointment> getAll() {
//        return appointmentDao.findAll();
//    }
//
////    public Optional<Appointment> getById(Long id) {
////        return appointmentDao.findById(id);
////    }
//
//    public Optional<Appointment> create(Appointment body) {
//        return Optional.empty();
//    }
//
//    public Optional<Appointment> update(Appointment body, Long id) {
//        return Optional.empty();
//    }
//
//    public void delete(Long id) {
//
//    }
//}
