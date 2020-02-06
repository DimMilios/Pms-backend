package com.pms.security;

import com.pms.model.userprofile.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * The type Application user.
 */
public class ApplicationUser implements UserDetails {

    private final UserProfile userProfile;
    private final String username;
    private final String password;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    /**
     * Instantiates a new Application user.
     *
     * @param userProfile             the user profile
     * @param grantedAuthorities      the granted authorities
     * @param isAccountNonExpired     the is account non expired
     * @param isAccountNonLocked      the is account non locked
     * @param isCredentialsNonExpired the is credentials non expired
     * @param isEnabled               the is enabled
     */
    public ApplicationUser(UserProfile userProfile,
                           Set<? extends GrantedAuthority> grantedAuthorities,
                           boolean isAccountNonExpired,
                           boolean isAccountNonLocked,
                           boolean isCredentialsNonExpired,
                           boolean isEnabled) {
        this.userProfile = userProfile;
        this.username = userProfile.getUsername();
        this.password = userProfile.getPassword();
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    /**
     * Gets user profile.
     *
     * @return the user profile
     */
    public UserProfile getUserProfile() {
        return userProfile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
