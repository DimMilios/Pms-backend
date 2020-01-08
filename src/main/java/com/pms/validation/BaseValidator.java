package com.pms.validation;

import com.pms.model.userprofile.UserProfile;

public abstract class BaseValidator implements Validator {

    private Validator next;

    public BaseValidator() {
    }

    @Override
    public Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    @Override
    public abstract boolean isValid(UserProfile userProfile);

    protected boolean checkNext(UserProfile userProfile) {
        if (next == null) {
            return true;
        }
        return next.isValid(userProfile);
    }
}
