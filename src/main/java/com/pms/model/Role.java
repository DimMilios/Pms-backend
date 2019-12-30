package com.pms.model;

public enum Role {
    USER, STAFF, ADMIN;

    public static Role toRole(String str) {
        return Role.valueOf(str);
    }
}
