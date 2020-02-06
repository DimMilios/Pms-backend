package com.pms.model.userprofile;

/**
 * The interface User profile optional field builder.
 */
public interface IUserProfileOptionalFieldBuilder {

    /**
     * With id user profile optional field builder.
     *
     * @param id the id
     * @return the user profile optional field builder
     */
    IUserProfileOptionalFieldBuilder withId(Long id);

    /**
     * Build user profile.
     *
     * @return the user profile
     */
    UserProfile build();
}
