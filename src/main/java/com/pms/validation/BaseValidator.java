package com.pms.validation;

import com.pms.model.userprofile.UserProfile;

/**
 * The type Base validator.
 */
public abstract class BaseValidator implements Validator {

    private Validator next;

    /**
     * Instantiates a new Base validator.
     */
    public BaseValidator() {
    }

    @Override
    public Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    @Override
    public abstract boolean isValid(UserProfile userProfile);

    /**
     * Check next boolean.
     *
     * @param userProfile the user profile
     * @return the boolean
     */
    protected boolean checkNext(UserProfile userProfile) {
        if (next == null) {
            return true;
        }
        return next.isValid(userProfile);
    }
}
