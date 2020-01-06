package com.pms.validation;

import com.pms.middleware.UserProfileMiddleware;
import com.pms.model.userprofile.UserProfile;

import java.util.regex.Pattern;

public class UsernameValidator extends UserProfileMiddleware {

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

    public UsernameValidator(UserProfile userProfile) {
        super(userProfile);
    }

    @Override
    public boolean isValid(UserProfile userProfile) {
        Pattern pat = Pattern.compile(regex);
        if (userProfile.getUsername() == null)
            return false;
        return pat.matcher(userProfile.getUsername()).matches();
    }


    @Override
    public boolean checkNext(UserProfile userProfile) {
        return checkNext(userProfile);
    }
}
