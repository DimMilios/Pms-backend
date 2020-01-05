package com.pms.model;

public enum Role {
    USER, STAFF, ADMIN;

    public static Role toRole(String str) {
        return Role.valueOf(str.toUpperCase());
    }

    public static String fromRole(Role role) {
        return String.valueOf(role.toString());
    }
}
