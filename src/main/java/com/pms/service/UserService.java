//package com.pms.service;
//
//import com.pms.dao.UserProfileDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//@Service
//public class UserService implements UserDetailsService {
//
//    private final UserProfileDao userProfileDao;
//
//    @Autowired
//    public UserService(UserProfileDao userProfileDao) {
//        this.userProfileDao = userProfileDao;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userProfileDao
//                .findByUsername(username)
//                .orElseThrow(() ->
//                    new UsernameNotFoundException(String.format("Username %s not found", username))
//                );
//    }
//}
