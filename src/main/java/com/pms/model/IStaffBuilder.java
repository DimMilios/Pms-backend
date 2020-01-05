package com.pms.model;

import java.util.Set;

public interface IStaffBuilder {

    IStaffBuilder withUserProfile(UserProfile userProfile);

    IStaffBuilder withFullName(FullName fullname);

    IStaffBuilder withAddress(Address address);

    IStaffOptionalFieldBuilder withPhoneNumbers(Set<PhoneNumber> phoneNumbers);
}
