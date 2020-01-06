package com.pms.validation;

import com.pms.middleware.UserProfileMiddleware;
import com.pms.model.userprofile.UserProfile;

import java.util.regex.Pattern;

public class EmailValidator extends UserProfileMiddleware {

    private static final String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]" +
            "|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

//    private static EmailValidator INSTANCE;
//
//    public static EmailValidator getValidator() {
//        if (INSTANCE == null) {
//            INSTANCE = new EmailValidator();
//        }
//
//        return INSTANCE;
//    }

    public EmailValidator(UserProfile userProfile) {
        super(userProfile);
    }

//    public boolean isValid(String email) {
//        Pattern pat = Pattern.compile(regex);
//        if (email == null)
//            return false;
//        return pat.matcher(email).matches();
//    }

    @Override
    public boolean isValid(UserProfile userProfile) {
        Pattern pat = Pattern.compile(regex);
        if (userProfile.getEmail() == null)
            return false;
        return pat.matcher(userProfile.getEmail()).matches();
    }

    @Override
    public boolean checkNext(UserProfile userProfile) {
        return checkNext(userProfile);
    }
}
