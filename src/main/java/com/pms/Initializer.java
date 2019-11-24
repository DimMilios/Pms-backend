package com.pms;

import com.pms.dao.UserDao;
import com.pms.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {

    private UserDao userDao;

    @Autowired
    public Initializer(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void run(String... args) throws Exception {
        UserProfile user1 = new UserProfile();
        UserProfile user2 = new UserProfile();




    }
}
