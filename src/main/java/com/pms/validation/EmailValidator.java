package com.pms.validation;

import com.google.common.base.Strings;
import com.pms.model.userprofile.UserProfile;

import java.util.regex.Pattern;

/**
 * The type Email validator.
 */
public class EmailValidator extends BaseValidator {

    private static final String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]" +
            "|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";


    /**
     * Instantiates a new Email validator.
     */
    public EmailValidator() {
    }


    @Override
    public boolean isValid(UserProfile userProfile) {
        Pattern pat = Pattern.compile(regex);
        String email = userProfile.getEmail();
        if (Strings.isNullOrEmpty(email) || !pat.matcher(email).matches()) {
            throw new RuntimeException("Email is empty or wrong format");
        }
        return checkNext(userProfile);
    }
}
