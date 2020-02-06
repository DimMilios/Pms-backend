package com.pms.model;

/**
 * The enum Sex.
 */
public enum Sex {
    /**
     * Male sex.
     */
    MALE,
    /**
     * Female sex.
     */
    FEMALE,
    /**
     * Other sex.
     */
    OTHER;

    /**
     * To sex sex.
     *
     * @param str the str
     * @return the sex
     */
    public static Sex toSex(String str) {
        return Sex.valueOf(str.toUpperCase());
    }
}
