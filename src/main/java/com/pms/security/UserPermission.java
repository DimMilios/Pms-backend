package com.pms.security;

/**
 * The enum User permission.
 */
public enum UserPermission {
    /**
     * Staff read user permission.
     */
    STAFF_READ("staff:read"),
    /**
     * Staff write user permission.
     */
    STAFF_WRITE("staff:write"),
    /**
     * Patient read user permission.
     */
    PATIENT_READ("patient:read"),
    /**
     * Patient write user permission.
     */
    PATIENT_WRITE("patient:write"),
    /**
     * Appointment read user permission.
     */
    APPOINTMENT_READ("appointment:read"),
    /**
     * Appointment write user permission.
     */
    APPOINTMENT_WRITE("appointment:write"),
    /**
     * Prescription read user permission.
     */
    PRESCRIPTION_READ("prescription:read"),
    /**
     * Prescription write user permission.
     */
    PRESCRIPTION_WRITE("prescription:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    /**
     * Gets permission.
     *
     * @return the permission
     */
    public String getPermission() {
        return permission;
    }
}
