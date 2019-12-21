package com.pms;

import com.pms.dao.AdminDao;
import com.pms.dao.StaffDao;
import com.pms.dao.UserProfileDao;
import com.pms.model.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    public void run(String... args) throws Exception {
        UserProfile user1 = new UserProfile("george123", "123", "george123@test.com", Role.ADMIN);
////        UserProfile userProfile1 = new UserProfile("mike123", "123", "mike123@test.com", Role.ADMIN);
////        UserProfile user2 = new UserProfile();
        System.out.println(user1.toString());
//        userProfileDao.save(user1);
////        userProfileDao.save(userProfile1);
////        admin.setRole(Role.ADMIN);
////        admin.setRole();
////        admin.setUserProfile(user1);
////
////        adminDao.save(admin);
////
////        System.out.println();
//
        UserProfile adminProfile = userProfileDao.findById((long) 30).get();
//
////        UserProfile adminProfile = userProfileDao.findById((long) 6).get();
////        System.out.println(adminProfile.toString());
        Admin admin1 = createAdmin(adminProfile.getId());
////        Admin admin = new Admin();
////        adminDao.save(admin);
////        System.out.println(admin.toString());
////        Admin admin = initAdmin(admin1, adminProfile);
        System.out.println(admin1.toString());
////
////
////
////        deleteAdmin(adminDao.findById((long) 27).get());
    }

//    @Override
//    public void run(String... args) throws Exception {
////        UserProfile userProfile1 = new UserProfile("john123", "123", "john123@test.com", Role.STAFF);
////        userProfileDao.save(userProfile1);
//
//        UserProfile staffProfile = userProfileDao.findById((long) 28).get();
//        Staff staff = createStaff(staffProfile);
//
//        PhoneNumber[] src = {
//                new PhoneNumber("54353534534"),
//                new PhoneNumber("68945534534"),
//                new PhoneNumber("54353568674")
//        };
//        Set<PhoneNumber> phoneNumbers =
//                new HashSet<PhoneNumber>(Arrays.asList(src));
//
//        staff = initStaff(staff, staffProfile, phoneNumbers);
//        System.out.println(staff.toString());
//    }

    private Admin initAdmin(Admin ad, UserProfile adminProfile) {
        Admin admin = adminDao.findById(ad.getId()).get();
        admin.setUserProfile(adminProfile);
        admin.setRole(adminProfile.getRole());
        admin.setProfileId(adminProfile.getId());
        return adminDao.save(admin);
    }

    private Admin createAdmin(Long userProfileId) {
        Admin admin = new Admin();
        UserProfile userProfile = userProfileDao.findById(userProfileId).get();
        admin.setUserProfile(userProfile);
        admin.setProfileId(userProfile.getId());
        admin.setRole(userProfile.getRole());
        return adminDao.save(admin);
    }

    private void deleteAdmin(Admin admin) {
        adminDao.delete(admin);
    }

//    private Staff createStaff(UserProfile userProfile) {
//        Staff staff = new Staff();
//        return staffDao.save(staff);
//    }
//
//    private Staff initStaff(Staff staff, UserProfile userProfile, Set<PhoneNumber> phoneNumbers) {
//        Staff staff1 = staffDao.findById(staff.getId()).get();
//        staff1.setUserProfile(userProfile);
//        staff1.setRole(userProfile.getRole());
//        staff1.setProfileId(userProfile.getId());
//        staff1.setPhoneNumbers(phoneNumbers);
//        return staffDao.save(staff1);
//    }
}
