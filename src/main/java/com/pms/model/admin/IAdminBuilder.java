package com.pms.model.admin;

import com.pms.model.FullName;
import com.pms.model.userprofile.UserProfile;

public interface IAdminBuilder {

    IAdminBuilder withUserProfile(UserProfile userProfile);

    IAdminOptionalFieldBuilder withFullName(FullName fullname);
}
