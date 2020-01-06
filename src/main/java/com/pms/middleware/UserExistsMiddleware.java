//package com.pms.middleware;
//
//import com.pms.dao.UserProfileDao;
//import com.pms.model.userprofile.UserProfile;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserExistsMiddleware extends UserProfileMiddleware {
//
//    public UserExistsMiddleware(UserProfileDao userProfileDao) {
//        super(userProfileDao);
//    }
//
//    @Override
//    public boolean check(UserProfile userProfile) {
//        if (userProfileDao.existsById(userProfile.getId()) ||
//            userProfileDao.existsByEmail(userProfile.getEmail()) ||
//            userProfileDao.existsByUsername(userProfile.getUsername())) {
//
//            return false;
//        }
//
//        return checkNext(userProfile);
//    }
//}
