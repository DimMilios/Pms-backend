package com.pms.validation;

import com.pms.model.userprofile.UserProfile;

/**
 * The interface Validator.
 */
public interface Validator {

    /**
     * Sets next.
     *
     * @param next the next
     * @return the next
     */
    Validator setNext(Validator next);

    /**
     * Is valid boolean.
     *
     * @param userProfile the user profile
     * @return the boolean
     */
    boolean isValid(UserProfile userProfile);

}
