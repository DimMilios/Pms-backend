package com.pms.service;

import com.pms.dao.UserProfileDao;
import com.pms.model.userprofile.UserProfile;
import com.pms.security.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static com.pms.security.UserRole.ADMIN;

@Service
public class UserService implements UserDetailsService {

    private final UserProfileDao userProfileDao;

    @Autowired
    public UserService(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserProfile userProfile = userProfileDao.findByUsername(username).get();

            return new ApplicationUser(userProfile, ADMIN.getGrantedAuthorities(), true, true, true, true);
        } catch (UsernameNotFoundException ex) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
    }
}
