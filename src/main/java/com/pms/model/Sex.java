package com.pms.model;

public enum Sex {
    MALE, FEMALE, OTHER;

    public static Sex toSex(String str) {
        return Sex.valueOf(str.toUpperCase());
    }
}
