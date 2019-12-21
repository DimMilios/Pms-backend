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
import java.util.List;
import java.util.Set;
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

    @Override
    public void run(String... args) throws Exception {
        List<UserProfile> userProfiles = Arrays.asList(
                new UserProfile("george123", "123", "george123@test.com", Role.ADMIN),
                new UserProfile("gge123", "123", "gge123@test.com", Role.ADMIN),
                new UserProfile("maria123", "123", "maria123@test.com", Role.STAFF),
                new UserProfile("nikos123", "123", "nikos123@test.com", Role.USER)
        );



        userProfiles.stream()
                .forEach(userProfile -> userProfileDao.save(userProfile));

//        List<Admin> admins = userProfiles.stream()
//                .filter(userProfile -> userProfile.getRole().equals(Role.ADMIN))
//                .map(userProfile -> adminDao.save(new Admin(userProfile, userProfile.getId())))
//                .collect(Collectors.toList());
        List<Admin> admins = Arrays.asList(
                new Admin(userProfiles.get(0), userProfiles.get(0).getId(), ("George", "Papas", "Ioannis"))
        );

        userProfiles.stream()
                .forEach(System.out::println);

        admins.stream()
                .forEach(System.out::println);


    }


}
