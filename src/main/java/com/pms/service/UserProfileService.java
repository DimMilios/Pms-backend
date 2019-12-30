package com.pms.service;
//
//import com.pms.dao.UserProfileDao;
//import com.pms.middleware.UserProfileMiddleware;
//import com.pms.model.UserProfile;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserProfileService {
//
//    private UserProfileDao userProfileDao;
//
//    private UserProfileMiddleware middleware;
//
//    /**
//     * Client passes a chain of object to server. This improves flexibility and
//     * makes testing the server class easier.
//     */
//    public void setMiddleware(UserProfileMiddleware middleware) {
//        this.middleware = middleware;
//    }
//
//    @Autowired
//    public UserProfileService(UserProfileDao userProfileDao) {
//        this.userProfileDao = userProfileDao;
//
//
//    }
//
//    public Optional<UserProfile> createUserProfile(UserProfile profileToAdd) {
//
//    }
//}
