package com.pms.validation;

import com.pms.model.userprofile.UserProfile;

import java.util.regex.Pattern;

/**
 * The type Username validator.
 */
public class UsernameValidator extends BaseValidator {

    private static final String regex = "^(?=.{3,60}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

    /**
     * Instantiates a new Username validator.
     */
    public UsernameValidator() {

    }


    @Override
    public boolean isValid(UserProfile userProfile) {
        Pattern pat = Pattern.compile(regex);
        if (userProfile.getUsername() == null
                || !pat.matcher(userProfile.getUsername()).matches()) {
            throw new RuntimeException("Username is empty or wrong format");
        }
        return checkNext(userProfile);
    }



}
