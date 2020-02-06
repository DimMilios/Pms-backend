package com.pms.model.admin;

import com.pms.model.FullName;
import com.pms.model.userprofile.UserProfile;

/**
 * The interface Admin builder.
 */
public interface IAdminBuilder {

    /**
     * With user profile admin builder.
     *
     * @param userProfile the user profile
     * @return the admin builder
     */
    IAdminBuilder withUserProfile(UserProfile userProfile);

    /**
     * With full name admin optional field builder.
     *
     * @param fullname the fullname
     * @return the admin optional field builder
     */
    IAdminOptionalFieldBuilder withFullName(FullName fullname);
}
