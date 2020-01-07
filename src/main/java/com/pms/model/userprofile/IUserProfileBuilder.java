package com.pms.model.userprofile;


public interface IUserProfileBuilder {

    IUserProfileBuilder withUsername(String username);

    IUserProfileBuilder withPassword(String password);

    IUserProfileBuilder withEmail(String email);

    IUserProfileOptionalFieldBuilder withRole(String role);
}

