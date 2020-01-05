package com.pms.model;

public interface IUserProfileOptionalFieldBuilder {

    IUserProfileOptionalFieldBuilder withId(Long id);

    UserProfile build();
}
