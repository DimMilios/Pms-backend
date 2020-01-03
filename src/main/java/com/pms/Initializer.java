package com.pms;

import com.pms.dao.AdminDao;
import com.pms.dao.StaffDao;
import com.pms.dao.UserProfileDao;
import com.pms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Initializer implements CommandLineRunner {

    private UserProfileDao userProfileDao;
    private AdminDao adminDao;
    private StaffDao staffDao;

    @Autowired
    public Initializer(UserProfileDao userProfileDao, AdminDao adminDao, StaffDao staffDao) {
        this.userProfileDao = userProfileDao;
        this.adminDao = adminDao;
        this.staffDao = staffDao;
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

    @Override
    public void run(String ...args) throws Exception {
        List<Staff> staffList = Arrays.asList(
                new Doctor(),
                new Doctor(),
                new LabStaff(),
                new LabStaff()
        );
        List<UserProfile> userProfiles = Arrays.asList(
                new UserProfile("george123", "123", "george123@test.com", Role.STAFF),
                new UserProfile("gge123", "123", "gge123@test.com", Role.STAFF),
                new UserProfile("maria123", "123", "maria123@test.com", Role.STAFF),
                new UserProfile("nikos123", "123", "nikos123@test.com", Role.STAFF)
        );


        Set<PhoneNumber> phoneNumbers = new HashSet<>(Arrays.asList(
//                new PhoneNumber("281035354"),
//                new PhoneNumber("281035861"),
                new PhoneNumber("281007892")
        ));

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

        FullName name = new FullName();
        name.setFirstName("George");
        name.setLastName("Papas");
        name.setFatherName("John");



        staffList.get(0).setUserProfile(userProfiles.get(2));
        staffList.get(1).setUserProfile(userProfiles.get(3));
        staffList.get(2).setUserProfile(userProfiles.get(0));
        staffList.get(3).setUserProfile(userProfiles.get(1));

        staffList.stream()
                .forEach(staff -> staff.setPhoneNumbers(phoneNumbers));


        staffList.stream()
            .forEach(staff -> {
                Random rand = new Random();
                int random = rand.nextInt(2);
                staff.setFullName(name);
                staff.setAddress(addresses.get(random));
                staffDao.save(staff);
            });

        staffList.stream()
                .forEach(System.out::println);
    }


}
