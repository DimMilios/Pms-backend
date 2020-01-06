package com.pms.model.admin;

public interface IAdminOptionalFieldBuilder {

    IAdminOptionalFieldBuilder withId(Long id);

    Admin build();
}
