package com.pms.model.userprofile;

import com.pms.model.Role;
import com.pms.validation.EmailValidator;
import com.pms.validation.UsernameValidator;
import com.pms.validation.Validator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

public class UserProfileBuilder implements IUserProfileBuilder, IUserProfileOptionalFieldBuilder {

    private List<Consumer<UserProfile>> operations;

    private Validator validator;

    private static UserProfileBuilder INSTANCE;

    private PasswordEncoder passwordEncoder;

    public static UserProfileBuilder userProfile() {
        if (INSTANCE == null) {
            INSTANCE = new UserProfileBuilder();
        }

        return INSTANCE;
    }

    private UserProfileBuilder() {
        this.operations = new ArrayList<>();
        this.passwordEncoder = new BCryptPasswordEncoder(10);
        initValidationChain();
    }

    private void initValidationChain() {
        this.validator = new EmailValidator();
        this.validator.setNext(new UsernameValidator());
    }

    @Override
    public IUserProfileOptionalFieldBuilder withId(Long id) {
        operations.add(userProfile -> userProfile.setId(id));
        return this;
    }

    @Override
    public IUserProfileBuilder withUsername(String username) {
        operations.add(userProfile -> userProfile.setUsername(username));
        return this;
    }

    @Override
    public IUserProfileBuilder withPassword(String password) {
        operations.add(userProfile -> userProfile.setPassword(passwordEncoder.encode(password)));
        return this;
    }

    @Override
    public IUserProfileBuilder withEmail(String email) {
        operations.add(userProfile -> userProfile.setEmail(email));
        return this;
    }

    @Override
    public IUserProfileOptionalFieldBuilder withRole(String role) {
        operations.add(userProfile -> userProfile.setRole(Role.valueOf(role)));
        return this;
    }

    @Override
    public UserProfile build() {
        UserProfile userProfile = new UserProfile();
        operations.forEach(operation -> operation.accept(userProfile));
        validate(userProfile);
        return userProfile;
    }

    private void validate(UserProfile userProfile) throws RuntimeException {
//        validateRequiredField("username", userProfile.getUsername());
//        validateRequiredField("password", userProfile.getPassword());
//        validateRequiredField("email", userProfile.getEmail());
//
        if (!validator.isValid(userProfile)) {
            throw new RuntimeException("Invalid state, userProfile [" +
                    userProfile + "] may not be null or empty.");
        }
    }

    private void validateRequiredField(String fieldName, String value) {
        if(isNull(value) || value.isEmpty()) {
            throw new IllegalStateException("Invalid state, field [" +
                    fieldName + "] may not be null or empty.");
        }
    }
}
