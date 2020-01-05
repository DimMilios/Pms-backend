package com.pms.model;

public interface IStaffOptionalFieldBuilder {

    IStaffOptionalFieldBuilder withId(Long id);

    Staff build();
}
