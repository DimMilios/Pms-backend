package com.pms;

import com.pms.dao.*;
import com.pms.model.*;
import com.pms.model.admin.Admin;
import com.pms.model.admin.AdminBuilder;
import com.pms.model.patient.Inpatient;
import com.pms.model.patient.InpatientBuilder;
import com.pms.model.patient.Patient;
import com.pms.model.patient.PatientBuilder;
import com.pms.model.staff.Staff;
import com.pms.model.staff.StaffBuilder;
import com.pms.model.userprofile.UserProfile;
import com.pms.model.userprofile.UserProfileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Initializer implements CommandLineRunner {

    private UserProfileDao userProfileDao;
    private AdminDao adminDao;
    private StaffDao staffDao;
    private PatientDao patientDao;
    private AppointmentDao appointmentDao;

    @Autowired
    public Initializer(UserProfileDao userProfileDao, AdminDao adminDao, StaffDao staffDao, PatientDao patientDao, AppointmentDao appointmentDao) {
        this.userProfileDao = userProfileDao;
        this.adminDao = adminDao;
        this.staffDao = staffDao;
        this.patientDao = patientDao;
        this.appointmentDao = appointmentDao;
    }

//    @Override
//    public void run(String... args) throws Exception {
//        List<UserProfile> userProfiles = Arrays.asList(
//                new UserProfile("george123", "123", "george123@test.com", Role.ADMIN),
//                new UserProfile("gge123", "123", "gge123@test.com", Role.ADMIN),
//                new UserProfile("maria123", "123", "maria123@test.com", Role.STAFF),
//                new UserProfile("nikos123", "123", "nikos123@test.com", Role.USER)
//        );
//
//        FullName name = new FullName();
//        name.setFirstName("George");
//        name.setLastName("Papas");
//        name.setFatherName("John");
//
//        userProfiles.stream()
//                .forEach(userProfile -> userProfileDao.save(userProfile));
////
//        List<Admin> admins = userProfiles.stream()
//                .filter(userProfile -> userProfile.getRole().equals(Role.ADMIN))
//                .map(userProfile -> adminDao.save(new Admin(userProfile, name)))
//                .collect(Collectors.toList());
//
////        List<Admin> admins = Arrays.asList(
////                new Admin(userProfiles.get(0), userProfiles.get(0).getId(), name),
////                new Admin(userProfiles.get(1), userProfiles.get(1).getId(), name)
////        );
//
////        List<Admin> admins = Arrays.asList(
////                new Admin(),
////                new Admin(),
////                new Admin(),
////                new Admin(),
////                new Admin()
////        );
//
//        adminDao.findAll()
//                .forEach(admin -> adminDao.save(admin));
//
//        userProfiles.stream().forEach(System.out::println);
//
//        admins.stream()
//                .forEach(System.out::println);
//
//
//    }

//    @Override
//    public void run(String ...args) throws Exception {
//        List<Staff> staffList = Arrays.asList(
//                new Doctor(),
//                new Doctor(),
//                new LabStaff(),
//                new LabStaff()
//        );
//        List<UserProfile> userProfiles = Arrays.asList(
//                new UserProfile("george123", "123", "george123@test.com", Role.STAFF),
//                new UserProfile("gge123", "123", "gge123@test.com", Role.STAFF),
//                new UserProfile("maria123", "123", "maria123@test.com", Role.STAFF),
//                new UserProfile("nikos123", "123", "nikos123@test.com", Role.STAFF)
//        );
//
//
//        Set<PhoneNumber> phoneNumbers = new HashSet<>(Arrays.asList(
////                new PhoneNumber("281035354"),
////                new PhoneNumber("281035861"),
//                new PhoneNumber("281007892")
//        ));
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
//        FullName name = new FullName();
//        name.setFirstName("George");
//        name.setLastName("Papas");
//        name.setFatherName("John");
//
//
//
//        staffList.get(0).setUserProfile(userProfiles.get(2));
//        staffList.get(1).setUserProfile(userProfiles.get(3));
//        staffList.get(2).setUserProfile(userProfiles.get(0));
//        staffList.get(3).setUserProfile(userProfiles.get(1));
//
//        staffList.stream()
//                .forEach(staff -> staff.setPhoneNumbers(phoneNumbers));
//
//
//        staffList.stream()
//            .forEach(staff -> {
//                Random rand = new Random();
//                int random = rand.nextInt(2);
//                staff.setFullName(name);
//                staff.setAddress(addresses.get(random));
//                staffDao.save(staff);
//            });
//
//        staffList.stream()
//                .forEach(System.out::println);
//    }


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
//                new UserProfile("george123", "123", "george123@test.com", Role.USER),
//                new UserProfile("gge123", "123", "gge123@test.com", Role.USER),
//                new UserProfile("maria123", "123", "maria123@test.com", Role.USER),
//                new UserProfile("nikos123", "123", "nikos123@test.com", Role.USER)
//        );
//
//        userProfiles.stream()
//                .forEach(userProfile -> userProfileDao.save(userProfile));
//
//        FullName name = new FullName();
//        name.setFirstName("George");
//        name.setLastName("Papas");
//        name.setFatherName("John");
//
//        patientList.get(0).setSsn((long) 435566622);
//        patientList.get(1).setSsn((long) 987666622);
//        patientList.get(2).setSsn((long) 067766622);
//        patientList.get(3).setSsn((long) 123666622);
//
//        patientList.stream().forEach(patient -> patient.setFullName(name));
//
////        patientList.get(0).setUserProfile(userProfiles.get(2));
////        patientList.get(1).setUserProfile(userProfiles.get(3));
////        patientList.get(2).setUserProfile(userProfiles.get(0));
////        patientList.get(3).setUserProfile(userProfiles.get(1));
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
//        Inpatient inp1 = new Inpatient();
//        Inpatient inp2 = new Inpatient();
//
//        long timeNow = System.currentTimeMillis();
//
//        inp1.setAdmitDate(new Timestamp(timeNow));
//        inp2.setAdmitDate(new Timestamp(timeNow));
//
//        inp1.setSsn((long) 567760098);
//        inp2.setSsn((long) 123669988);
//
////        patientDao.save(inp1);
////        patientDao.save(inp2);
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
//        patientList.stream()
//                .forEach(patient -> patientDao.save(patient));
//
//        Doctor doc1 = new Doctor();
//        doc1.setAddress(addresses.get(0));
//        staffDao.save(doc1);
//
////        AppointmentKey key = new AppointmentKey();
////        key.setDoctorId(doc1.getId());
////        key.setPatientSsn(inp1.getSsn());
////
////        Appointment appointment = new Appointment();
////        appointment.setId(key);
////        appointment.setDoctor(doc1);
////        appointment.setPatient(inp1);
////        appointment.setAppointmentDate(new Timestamp(timeNow));
////
////        appointmentDao.save(appointment);
//    }


    @Override
    public void run(String... args) throws Exception {

        UserProfile userProfile = UserProfileBuilder
                .userProfile()
                .withEmail("test@test.com")
                .withUsername("nikos123")
                .withPassword("123")
                .withRole("USER")
                .build();

        List<Address> addresses = Arrays.asList(
                new Address(),
                new Address()
        );

        FullName name = new FullName();
        name.setFirstName("George");
        name.setLastName("Papas");
        name.setFatherName("John");

        addresses.get(0).setCity("Aquila d'Arroscia");
        addresses.get(0).setStreetAddress("234-1556 Auctor Av.");
        addresses.get(0).setZipCode(934356);

        addresses.get(1).setCity("Port Hope");
        addresses.get(1).setStreetAddress("1025 Pharetra Av.");
        addresses.get(1).setZipCode(352061);

        Set<PhoneNumber> phoneNumbers = new HashSet<>(Arrays.asList(
                new PhoneNumber("281035354"),
                new PhoneNumber("281035861"),
                new PhoneNumber("281007892")
        ));

//        Staff doc = StaffBuilder.staff()
//                .withAddress(addresses.get(0))
//                .withFullName(name)
//                .withUserProfile(userProfile)
//                .withPhoneNumbers(phoneNumbers)
//                .build();
//
//        staffDao.save(doc);
//
//
//        System.out.println(doc.toString());

        UserProfile userProfile1 = UserProfileBuilder.userProfile()
                .withEmail("kostas@test.com")
                .withUsername("kostas")
                .withPassword("123")
                .withRole("ADMIN")
                .build();

        Admin admin = AdminBuilder.admin()
                .withUserProfile(userProfile1)
                .withFullName(name)
                .build();

        adminDao.save(admin);

        userProfileDao.save(userProfile);

//        Patient patient = PatientBuilder
//                .patient()
//                .withSsn(435353L)
//                .withFullName(name)
//                .withOccupation("Teacher")
//                .withUserProfile(userProfile)
//                .withSex("MALE")
//                .withAppointments(null)
//                .build();
        long timeNow = System.currentTimeMillis();

        Inpatient patient1 = InpatientBuilder
                .inpatient()
                .withSsn(45545L)
                .withUserProfile(userProfile)
                .withAdmitDate(new Timestamp(timeNow))
//                .withSsn(435353L)
//                .withFullName(name)
//                .withOccupation("Teacher")
//                .withUserProfile(userProfile)
//                .withSex("MALE")
//                .withAppointments(null)
                .build();


        Patient patient = InpatientBuilder
                .inpatient()
                .withSsn(435353L)
                .withAdmitDate(new Timestamp(timeNow))
                .build();

        patientDao.save(patient);
        System.out.println(patient);

        patientDao.save(patient1);
        System.out.println(patient1);


//        EmailValidator validator = EmailValidator.getValidator();
//        System.out.println(validator.isValid(userProfile.getEmail()));



    }
}
