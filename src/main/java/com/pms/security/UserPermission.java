package com.pms.security;

public enum UserPermission {
    STAFF_READ("staff:read"),
    STAFF_WRITE("staff:write"),
    PATIENT_READ("patient:read"),
    PATIENT_WRITE("patient:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
