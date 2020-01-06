package com.pms.model.userprofile;

import com.pms.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

public class UserProfileBuilder implements IUserProfileBuilder, IUserProfileOptionalFieldBuilder {

    private List<Consumer<UserProfile>> operations;

    private static UserProfileBuilder INSTANCE;

    public static UserProfileBuilder userProfile() {
        if (INSTANCE == null) {
            INSTANCE = new UserProfileBuilder();
        }

        return INSTANCE;
    }

    private UserProfileBuilder() {
        this.operations = new ArrayList<>();
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
        operations.add(userProfile -> userProfile.setPassword(password));
        return this;
    }

    @Override
    public IUserProfileBuilder withEmail(String email) {
        operations.add(userProfile -> userProfile.setEmail(email));
        return this;
    }

    @Override
    public IUserProfileOptionalFieldBuilder withRole(String role) {
        operations.add(userProfile -> userProfile.setRole(Role.toRole(role)));
        return this;
    }

    @Override
    public UserProfile build() {
        UserProfile userProfile = new UserProfile();
        operations.forEach(operation -> operation.accept(userProfile));
        validate(userProfile);
        return userProfile;
    }

    private void validate(UserProfile userProfile) {
        validateRequiredField("username", userProfile.getUsername());
        validateRequiredField("password", userProfile.getPassword());
        validateRequiredField("email", userProfile.getEmail());
        validateRequiredField("role", String.valueOf(userProfile.getRole()));
    }

    private void validateRequiredField(String fieldName, String value) {
        if(isNull(value) || value.isEmpty()) {
            throw new IllegalStateException("Invalid state, field [" +
                    fieldName + "] may not be null or empty.");
        }
    }
}
