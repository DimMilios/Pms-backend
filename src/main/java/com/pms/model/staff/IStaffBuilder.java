package com.pms.model.staff;

import com.pms.model.Address;
import com.pms.model.FullName;
import com.pms.model.PhoneNumber;
import com.pms.model.userprofile.UserProfile;

import java.util.Set;

public interface IStaffBuilder {

    IStaffBuilder withUserProfile(UserProfile userProfile);

    IStaffBuilder withFullName(FullName fullname);

    IStaffBuilder withAddress(Address address);

    IStaffOptionalFieldBuilder withPhoneNumbers(Set<PhoneNumber> phoneNumbers);
}
