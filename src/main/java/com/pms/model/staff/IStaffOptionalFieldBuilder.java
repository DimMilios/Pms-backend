package com.pms.model.staff;

public interface IStaffOptionalFieldBuilder {

    IStaffOptionalFieldBuilder withId(Long id);

    Staff build();
}
