package com.pms.validation;

import com.pms.model.userprofile.UserProfile;

public interface Validator {

    Validator setNext(Validator next);

    boolean isValid(UserProfile userProfile);

}
