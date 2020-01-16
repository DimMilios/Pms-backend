package com.pms;

import com.pms.dao.*;
import com.pms.model.Address;
import com.pms.model.FullName;
import com.pms.model.Sex;
import com.pms.model.admin.Admin;
import com.pms.model.admin.AdminBuilder;
import com.pms.model.appointment.Appointment;
import com.pms.model.appointment.AppointmentBuilder;
import com.pms.model.appointment.AppointmentKey;
import com.pms.model.patient.Inpatient;
import com.pms.model.patient.Patient;
import com.pms.model.patient.PatientBuilder;
import com.pms.model.staff.Doctor;
import com.pms.model.staff.Staff;
import com.pms.model.staff.StaffBuilder;
import com.pms.model.userprofile.UserProfile;
import com.pms.model.userprofile.UserProfileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AppointInit implements CommandLineRunner {

    private UserProfileDao userProfileDao;
    private AdminDao adminDao;
    private StaffDao staffDao;
    private PatientDao patientDao;
    private AppointmentDao appointmentDao;
    private final DoctorDao doctorDao;

    @Autowired
    public AppointInit(UserProfileDao userProfileDao, AdminDao adminDao, StaffDao staffDao, PatientDao patientDao, AppointmentDao appointmentDao, DoctorDao doctorDao) {
        this.userProfileDao = userProfileDao;
        this.adminDao = adminDao;
        this.staffDao = staffDao;
        this.patientDao = patientDao;
        this.appointmentDao = appointmentDao;
        this.doctorDao = doctorDao;
    }

//    @Override
//    public void run(String... args) throws Exception {
//        List<Patient> patientList = Arrays.asList(
//                new Patient(),
//                new Inpatient(),
//                new Patient(),
//                new Inpatient()
//        );
//
//        List<UserProfile> userProfiles = Arrays.asList(
//                UserProfileBuilder.userProfile().withUsername("george123").withPassword("123").withEmail("george123@test.com").withRole("USER").build(),
//                UserProfileBuilder.userProfile().withUsername("gge123").withPassword("123").withEmail("gge123@test.com").withRole("USER").build(),
//                UserProfileBuilder.userProfile().withUsername("maria123").withPassword("123").withEmail("maria123@test.com").withRole("USER").build(),
//                UserProfileBuilder.userProfile().withUsername("nikos123").withPassword("123").withEmail("nikos123@test.com").withRole("USER").build()
//        );
//
//        userProfiles.forEach(userProfile -> userProfileDao.save(userProfile));
//
//        FullName name = new FullName();
//        name.setFirstName("George");
//        name.setLastName("Papas");
//        name.setFatherName("John");
//
//        patientList.get(0).setSsn((long) 435566622);
//        patientList.get(1).setSsn((long) 987666622);
//        patientList.get(2).setSsn((long) 14675346);
//        patientList.get(3).setSsn((long) 123666622);
//
//        patientList.forEach(patient -> patient.setFullName(name));
//
//        patientList.get(0).setOccupation("Teacher");
//        patientList.get(1).setOccupation("Driver");
//        patientList.get(2).setOccupation("Accountant");
//        patientList.get(3).setOccupation("Police Officer");
//
//        patientList.get(0).setSex(Sex.MALE);
//        patientList.get(1).setSex(Sex.FEMALE);
//        patientList.get(2).setSex(Sex.FEMALE);
//        patientList.get(3).setSex(Sex.MALE);
//
//        List<Address> addresses = Arrays.asList(
//            new Address(),
//            new Address()
//        );
//
//        addresses.get(0).setCity("Aquila d'Arroscia");
//        addresses.get(0).setStreetAddress("234-1556 Auctor Av.");
//        addresses.get(0).setZipCode(934356);
//
//        addresses.get(1).setCity("Port Hope");
//        addresses.get(1).setStreetAddress("1025 Pharetra Av.");
//        addresses.get(1).setZipCode(352061);
//
//        patientList.forEach(patient -> patientDao.save(patient));
//
//        Doctor doc1 = new Doctor();
//        doc1.setAddress(addresses.get(0));
////        staffDao.save(doc1);
//        doctorDao.save(doc1);
//
//        AppointmentKey key = new AppointmentKey();
//        key.setDoctorId(doc1.getId());
//        key.setPatientSsn(patientList.get(0).getSsn());
//
//        long timeNow = System.currentTimeMillis();
//
//        Appointment appointment = new Appointment();
//        appointment.setId(key);
//        appointment.setDoctor(doc1);
//        appointment.setPatient(patientList.get(0));
//        appointment.setAppointmentDate(new Timestamp(timeNow));
//
//        appointmentDao.save(appointment);
////        Appointment appointment = AppointmentBuilder
////                .appointment()
////                .withDoctor(doc1)
////                .withPatient(patientList.get(0))
////                .withAppointmentDate(new Timestamp(timeNow))
////                .build();
//
//        System.out.println(appointment);
//    }

    List<UserProfile> getUserProfiles() {
        return Arrays.asList(
                UserProfileBuilder.userProfile().withUsername("george123").withPassword("123").withEmail("george123@test.com").withRole("ADMIN").build(),
                UserProfileBuilder.userProfile().withUsername("gge123").withPassword("123").withEmail("gge123@test.com").withRole("USER").build(),
                UserProfileBuilder.userProfile().withUsername("maria123").withPassword("123").withEmail("maria123@test.com").withRole("STAFF").build(),
                UserProfileBuilder.userProfile().withUsername("nikos123").withPassword("123").withEmail("nikos123@test.com").withRole("USER").build()
        );
    }

    List<Patient> getPatients(List<UserProfile> profiles) {
        Patient p1 = PatientBuilder
                .patient()
                .withSsn(43242L)
                .withUserProfile(profiles.get(3))
                .withSex("MALE")
                .withOccupation("Teacher")
                .build();

        Patient p2 = PatientBuilder
                .patient()
                .withSsn(94342L)
                .withUserProfile(profiles.get(1))
                .withSex("MALE")
                .withOccupation("Lawyer")
                .build();

        patientDao.save(p1);
        patientDao.save(p2);

        return Arrays.asList(p1, p2);
    }

    List<Address> getAddresses() {
        List<Address> addresses = Arrays.asList(
                new Address(),
                new Address()
        );

        addresses.get(0).setCity("Aquila d'Arroscia");
        addresses.get(0).setStreetAddress("234-1556 Auctor Av.");
        addresses.get(0).setZipCode(934356);

        addresses.get(1).setCity("Port Hope");
        addresses.get(1).setStreetAddress("1025 Pharetra Av.");
        addresses.get(1).setZipCode(352061);

        return addresses;
    }

    List<Doctor> getDoctors(List<Address> addresses, List<UserProfile> profiles) {
        Doctor d1 = new Doctor();
        d1.setAddress(addresses.get(0));
        d1.setUserProfile(profiles.get(2));

        Doctor d2 = new Doctor();
        d2.setAddress(addresses.get(1));
        d2.setUserProfile(profiles.get(0));

        staffDao.save(d1);
        staffDao.save(d2);

        return Arrays.asList(d1, d2);
    }

//    @Override
//    public void run(String... args) throws Exception {
////        List<UserProfile> profiles = getUserProfiles();
////        List<Patient> patients = getPatients(profiles);
////        List<Address> addresses = getAddresses();
////        List<Doctor> doctors = getDoctors(addresses, profiles);
////
////        profiles.forEach(System.out::println);
////        patients.forEach(System.out::println);
////        addresses.forEach(System.out::println);
////        doctors.forEach(System.out::println);
//
//        UserProfile profile = UserProfileBuilder
//                .userProfile()
//                .withUsername("george123")
//                .withPassword("123")
//                .withEmail("george123@test.com").
//                        withRole("ADMIN")
//                .build();
//        userProfileDao.save(profile);
//
//        Admin admin = AdminBuilder
//                .admin()
//                .withUserProfile(
//                        profile
//                ).withFullName(null)
//                .build();
//
//        adminDao.save(admin);
//        System.out.println(admin);
//    }


    @Override
    public void run(String... args) throws Exception {
        List<UserProfile> profiles = getUserProfiles();
//        List<Patient> patients = getPatients(profiles);
        List<Address> addresses = getAddresses();
//        List<Doctor> doctors = getDoctors(addresses, profiles);

        Address address = new Address();
        address.setCity("Aquila d'Arroscia");
        address.setStreetAddress("234-1556 Auctor Av.");
        address.setZipCode(934356);

        Patient patient = PatientBuilder
                .patient()
                .withSsn(3534534L)
                .withSex("MALE")
                .build();

        patientDao.save(patient);

        Doctor doctor = new Doctor();
        doctor.setAddress(address);
        doctor.setUserProfile(profiles.get(0));

        staffDao.save(doctor);

        AppointmentKey key = new AppointmentKey();
        key.setDoctorId(doctor.getId());
        key.setPatientSsn(patient.getSsn());

        long timeNow = System.currentTimeMillis();


        Appointment appointment = new Appointment();
        appointment.setId(key);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(new Timestamp(timeNow));

        appointmentDao.save(appointment);
        System.out.println(appointment);

    }
}
