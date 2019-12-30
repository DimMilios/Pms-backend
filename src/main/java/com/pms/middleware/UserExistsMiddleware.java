package com.pms.middleware;

import com.pms.dao.UserProfileDao;
import com.pms.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;

public class UserExistsMiddleware extends UserProfileMiddleware {

    private UserProfileDao userProfileDao;

    @Autowired
    public UserExistsMiddleware(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    @Override
    public boolean check(UserProfile userProfile) {
        if (userProfileDao.existsById(userProfile.getId()) ||
            userProfileDao.existsByEmail(userProfile.getEmail()) ||
            userProfileDao.existsByUsername(userProfile.getUsername())) {

            return false;
        }

        return checkNext(userProfile);
    }
}
