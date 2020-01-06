package com.pms.model.userprofile;

public interface IUserProfileOptionalFieldBuilder {

    IUserProfileOptionalFieldBuilder withId(Long id);

    UserProfile build();
}
