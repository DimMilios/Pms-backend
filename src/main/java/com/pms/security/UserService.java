package com.pms.security;

import com.pms.dao.UserProfileDao;
import com.pms.model.Role;
import com.pms.model.userprofile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * The type User service.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserProfileDao userProfileDao;

    /**
     * Instantiates a new User service.
     *
     * @param userProfileDao the user profile dao
     */
    @Autowired
    public UserService(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userProfileDao.findByUsername(username)
                .map(this::getUserDetails)
                .orElseThrow(() -> new
                    UsernameNotFoundException(String.format("Username %s not found", username)));
}

    private UserDetails getUserDetails(UserProfile userProfile) {
        if (userProfile.getRole() == Role.ADMIN) {
            return new ApplicationUser(
                    userProfile,
                    UserRole.ADMIN.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true);
        } else if (userProfile.getRole() == Role.STAFF) {
            return new ApplicationUser(
                    userProfile,
                    UserRole.STAFF.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true);
        } else if (userProfile.getRole() == Role.USER) {
            return new ApplicationUser(
                    userProfile,
                    UserRole.USER.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true);
        }
        return null;
    }
}
