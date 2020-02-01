package com.pms.validation;

import com.pms.model.userprofile.UserProfile;

import java.util.regex.Pattern;

public class UsernameValidator extends BaseValidator {

    private static final String regex = "^(?=.{3,60}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

//    private static UsernameValidator INSTANCE;
//
//    public static UsernameValidator getValidator() {
//        if (INSTANCE == null) {
//            INSTANCE = new UsernameValidator();
//        }
//
//        return INSTANCE;
//    }

    public UsernameValidator() {

    }


    @Override
    public boolean isValid(UserProfile userProfile) {
        Pattern pat = Pattern.compile(regex);
        if (userProfile.getUsername() == null
                || !pat.matcher(userProfile.getUsername()).matches()) {
//            return false;
            throw new RuntimeException("Username is empty or wrong format");
        }
        return checkNext(userProfile);
    }



}
