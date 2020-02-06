package com.pms.model.userprofile;


/**
 * The interface User profile builder.
 */
public interface IUserProfileBuilder {

    /**
     * With username user profile builder.
     *
     * @param username the username
     * @return the user profile builder
     */
    IUserProfileBuilder withUsername(String username);

    /**
     * With password user profile builder.
     *
     * @param password the password
     * @return the user profile builder
     */
    IUserProfileBuilder withPassword(String password);

    /**
     * With email user profile builder.
     *
     * @param email the email
     * @return the user profile builder
     */
    IUserProfileBuilder withEmail(String email);

    /**
     * With role user profile optional field builder.
     *
     * @param role the role
     * @return the user profile optional field builder
     */
    IUserProfileOptionalFieldBuilder withRole(String role);
}


