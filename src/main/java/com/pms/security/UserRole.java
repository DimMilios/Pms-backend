package com.pms.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pms.security.UserPermission.*;

public enum UserRole {
//    USER(Sets.newHashSet()),
//    STAFF(Sets.newHashSet(STAFF_READ, STAFF_WRITE)),
//    ADMIN(Sets.newHashSet(STAFF_READ, STAFF_WRITE, PATIENT_READ, PATIENT_WRITE));
    USER,
    STAFF,
    ADMIN;

//    private final Set<UserPermission> permissions;
//
//    UserRole(Set<UserPermission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<UserPermission> getPermissions() {
//        return permissions;
//    }
//
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
//        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return permissions;
        return Sets.newHashSet(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }
}
