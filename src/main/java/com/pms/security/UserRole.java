package com.pms.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pms.security.UserPermission.*;

/**
 * The enum User role.
 */
public enum UserRole {
    /**
     * User user role.
     */
    USER(Sets.newHashSet(PATIENT_READ, PATIENT_WRITE)),
    /**
     * Staff user role.
     */
    STAFF(Sets.newHashSet(STAFF_READ, STAFF_WRITE)),
    /**
     * Admin user role.
     */
    ADMIN(Sets.newHashSet(STAFF_READ, STAFF_WRITE, PATIENT_READ, PATIENT_WRITE,
            APPOINTMENT_READ, APPOINTMENT_WRITE, PRESCRIPTION_READ, PRESCRIPTION_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    /**
     * Gets permissions.
     *
     * @return the permissions
     */
    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    /**
     * Gets granted authorities.
     *
     * @return the granted authorities
     */
//
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
