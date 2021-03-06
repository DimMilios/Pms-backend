package com.pms;

import com.pms.dao.*;
import com.pms.model.Address;
import com.pms.model.FullName;
import com.pms.model.PhoneNumber;
import com.pms.model.Sex;
import com.pms.model.admin.Admin;
import com.pms.model.admin.AdminBuilder;
import com.pms.model.appointment.Appointment;
import com.pms.model.patient.Inpatient;
import com.pms.model.patient.Patient;
import com.pms.model.patient.PatientBuilder;
import com.pms.model.prescription.Medicine;
import com.pms.model.prescription.Prescription;
import com.pms.model.staff.*;
import com.pms.model.userprofile.UserProfile;
import com.pms.model.userprofile.UserProfileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class AppointInit implements CommandLineRunner {

    private UserProfileDao userProfileDao;
    private AdminDao adminDao;
    private StaffDao staffDao;
    private PatientDao patientDao;
    private InpatientDao inpatientDao;
    private AppointmentDao appointmentDao;
    private final DoctorDao doctorDao;
    private final MedicineDao medicineDao;
    private final PrescriptionDao prescriptionDao;

    @Autowired
    public AppointInit(UserProfileDao userProfileDao,
                       AdminDao adminDao,
                       StaffDao staffDao,
                       PatientDao patientDao,
                       InpatientDao inpatientDao,
                       AppointmentDao appointmentDao,
                       DoctorDao doctorDao,
                       MedicineDao medicineDao,
                       PrescriptionDao prescriptionDao) {
        this.userProfileDao = userProfileDao;
        this.adminDao = adminDao;
        this.staffDao = staffDao;
        this.patientDao = patientDao;
        this.inpatientDao = inpatientDao;
        this.appointmentDao = appointmentDao;
        this.doctorDao = doctorDao;
        this.medicineDao = medicineDao;
        this.prescriptionDao = prescriptionDao;
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
//        patientList.get(2).setOccupation("PaymentStaff");
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

    List<FullName> getFullNameList() {
        FullName f1 = new FullName();
        f1.setFirstName("George");
        f1.setLastName("Jones");
        f1.setFatherName("Mike");

        FullName f2 = new FullName();
        f2.setFirstName("John");
        f2.setLastName("Doe");
        f2.setFatherName("Rick");
        return Arrays.asList(f1,f2);
    }

    List<UserProfile> getUserProfiles() {
        return Arrays.asList(
                UserProfileBuilder.userProfile().withUsername("george123").withPassword("123").withEmail("george123@test.com").withRole("ADMIN").build(),
                UserProfileBuilder.userProfile().withUsername("gge123").withPassword("123").withEmail("gge123@test.com").withRole("USER").build(),
                UserProfileBuilder.userProfile().withUsername("maria123").withPassword("123").withEmail("maria123@test.com").withRole("STAFF").build(),
                UserProfileBuilder.userProfile().withUsername("nikos123").withPassword("123").withEmail("nikos123@test.com").withRole("USER").build(),
                UserProfileBuilder.userProfile().withUsername("nikos1234").withPassword("123").withEmail("nikos1234@test.com").withRole("STAFF").build(),
                UserProfileBuilder.userProfile().withUsername("nikos1235").withPassword("123").withEmail("nikos1235@test.com").withRole("STAFF").build(),
                UserProfileBuilder.userProfile().withUsername("nikos1236").withPassword("123").withEmail("nikos1236@test.com").withRole("STAFF").build(),
                UserProfileBuilder.userProfile().withUsername("nikos1237").withPassword("123").withEmail("nikos1237@test.com").withRole("USER").build(),
                UserProfileBuilder.userProfile().withUsername("nikos1238").withPassword("123").withEmail("nikos1238@test.com").withRole("USER").build(),
                UserProfileBuilder.userProfile().withUsername("nikos1239").withPassword("123").withEmail("nikos1239@test.com").withRole("USER").build()
        );
    }

    List<Patient> getPatients(List<UserProfile> profiles, List<FullName> names) {
        Patient p1 = PatientBuilder
                .patient()
                .withSsn(43242L)
                .withUserProfile(profiles.get(3))
                .withSex("MALE")
                .withOccupation("Teacher")
                .withFullName(names.get(0))
                .withBirthDate(LocalDate.parse("1976-01-31"))
                .build();

        Patient p2 = PatientBuilder
                .patient()
                .withSsn(94342L)
                .withUserProfile(profiles.get(1))
                .withSex("MALE")
                .withOccupation("Lawyer")
                .withFullName(names.get(1))
                .withBirthDate(LocalDate.parse("1955-06-24"))
                .build();

        patientDao.save(p1);
        patientDao.save(p2);

        return Arrays.asList(p1, p2);
    }

    List<Inpatient> getInpatients(List<UserProfile> profiles, List<FullName> names) {
        Inpatient p1 = new Inpatient();
        p1.setSsn(53453L);
        p1.setUserProfile(profiles.get(8));
        p1.setSex(Sex.MALE);
        p1.setOccupation("Teacher");
        p1.setBirthDate(LocalDate.parse("1989-05-23"));
        p1.setFullName(names.get(0));
        p1.setCurrentDiagnosis("Sick");
        p1.setAdmitDate(LocalDateTime.now());
        p1.setDischargeDate(LocalDate.parse("2020-05-03"));

        Inpatient p2 = new Inpatient();
        p2.setSsn(59843L);
        p2.setUserProfile(profiles.get(9));
        p2.setSex(Sex.FEMALE);
        p2.setOccupation("Builder");
        p2.setBirthDate(LocalDate.parse("1992-12-15"));
        p2.setFullName(names.get(1));
        p2.setCurrentDiagnosis("Corona");
        p2.setAdmitDate(LocalDateTime.now());
        p2.setDischargeDate(LocalDate.parse("2020-06-03"));

        inpatientDao.save(p1);
        inpatientDao.save(p2);

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

    List<Staff> getStaffList(List<Address> addresses, List<UserProfile> profiles) {
        LabStaff l1 = new LabStaff();
        l1.setAddress(addresses.get(1));
        l1.setUserProfile(profiles.get(4));

        PaymentStaff p1 = new PaymentStaff();
        p1.setAddress(addresses.get(1));
        p1.setUserProfile(profiles.get(5));

        PaymentStaff p2 = new PaymentStaff();
        p2.setAddress(addresses.get(1));
        p2.setUserProfile(profiles.get(6));

        Receptionist r1 = new Receptionist();
        r1.setAddress(addresses.get(0));
        r1.setUserProfile(profiles.get(7));

        staffDao.save(l1);
        staffDao.save(p1);
        staffDao.save(p2);
        staffDao.save(r1);

        return Arrays.asList(l1, p1, p2, r1);
    }

    List<Medicine> getMedicines() {
        Medicine m1 = new Medicine();
        m1.setCost(30.5);
        m1.setName("Lysopain");

        Medicine m2 = new Medicine();
        m2.setCost(22);
        m2.setName("Depon");

        medicineDao.save(m1);
        medicineDao.save(m2);

        return Arrays.asList(m1, m2);
    }

    Set<PhoneNumber> getPhoneNumbers() {
        return new HashSet<>(Arrays.asList(
                new PhoneNumber("281035354"),
                new PhoneNumber("281035861"),
                new PhoneNumber("281007892")
        ));
    }

    @Override
    public void run(String... args) {
        List<FullName> names = getFullNameList();
        List<UserProfile> profiles = getUserProfiles();
        List<Patient> patients = getPatients(profiles, names);
        List<Inpatient> inpatients = getInpatients(profiles, names);
        List<Address> addresses = getAddresses();
        List<Doctor> doctors = getDoctors(addresses, profiles);
        List<Staff> staffList = getStaffList(addresses, profiles);
        Set<PhoneNumber> numbers = getPhoneNumbers();
        List<Medicine> medicines = getMedicines();

        staffList.get(0).setPhoneNumbers(numbers);
        staffDao.save(staffList.get(0));

//        profiles.forEach(System.out::println);
////        patients.forEach(System.out::println);
////        addresses.forEach(System.out::println);
////        doctors.forEach(System.out::println);
////        staffList.forEach(System.out::println);
        inpatients.forEach(System.out::println);
        medicines.forEach(System.out::println);

        UserProfile profile = UserProfileBuilder
                .userProfile()
                .withUsername("adminName")
                .withPassword("123")
                .withEmail("admin123@test.com")
                .withRole("ADMIN")
                .build();
        userProfileDao.save(profile);


//        Admin admin = AdminBuilder
//                .admin()
//                .withUserProfile(profile)
//                .withFullName(null)
//                .build();
//
//        adminDao.save(admin);
//        System.out.println(admin);
//
//        Prescription pre1 = new Prescription();
//        pre1.setDoctor(doctors.get(0));
//        pre1.setPatient(patients.get(0));
//        pre1.setMedicine(medicines.get(0));
//        pre1.setDosage(500);
//        pre1.setPrescriptionDate(LocalDate.parse("2020-02-15"));
//
//        prescriptionDao.save(pre1);
//        System.out.println(pre1);

//
        Appointment appointment = new Appointment();
//        appointment.setId(key);
        appointment.setDoctor(doctors.get(0));
        appointment.setPatient(patients.get(0));
        appointment.setAppointmentDate(LocalDate.parse("2020-02-03"));
        appointment.setAppointmentTime(LocalTime.parse("15:30:00"));
//        appointment.setAppointmentDate(new Timestamp(System.currentTimeMillis()));

        Appointment app1 = new Appointment();
        app1.setDoctor(doctors.get(1));
        app1.setPatient(inpatients.get(0));
        app1.setAppointmentDate(LocalDate.parse("2020-06-23"));
        app1.setAppointmentTime(LocalTime.parse("14:00:00"));

        appointmentDao.save(appointment);
        appointmentDao.save(app1);
        System.out.println(appointment);
        System.out.println(app1);

        Collection<Doctor> availableForAppointment = doctorDao.findAvailableForAppointment(
                LocalDate.parse("2020-02-03"), LocalTime.parse("15:30:00"));

        availableForAppointment.forEach(System.out::println);
//    }
//
//
////    @Override
////    public void run(String... args) throws Exception {
////        List<UserProfile> profiles = getUserProfiles();
//////        List<Patient> patients = getPatients(profiles);
//////        List<Address> addresses = getAddresses();
//////        List<Doctor> doctors = getDoctors(addresses, profiles);
////
////        Address address = new Address();
////        address.setCity("Aquila d'Arroscia");
////        address.setStreetAddress("234-1556 Auctor Av.");
////        address.setZipCode(934356);
////
//////        Patient patient = PatientBuilder
//////                .patient()
//////                .withSsn(3534534L)
//////                .withSex("MALE")
//////                .build();
////        Patient patient = new Patient();
////        patient.setSsn(43434L);
////        patient.setUserProfile(profiles.get(0));
////
////        patientDao.save(patient);
////
////        Doctor doctor = new Doctor();
////        doctor.setAddress(address);
////        doctor.setUserProfile(profiles.get(2));
////
////        staffDao.save(doctor);
////
////        Appointment appointment = new Appointment();
////        appointment.setDoctor(doctor);
////        appointment.setPatient(patient);
////        appointment.setAppointmentDate(new Timestamp(System.currentTimeMillis()));
////
////        appointmentDao.save(appointment);
////        System.out.println(appointment);
////
////    }
//}
    }
}