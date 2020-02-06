package com.pms.model.staff;

import com.pms.model.Address;
import com.pms.model.FullName;
import com.pms.model.PhoneNumber;
import com.pms.model.userprofile.UserProfile;

import java.util.Set;

/**
 * The interface Staff builder.
 */
public interface IStaffBuilder {

    /**
     * With user profile staff builder.
     *
     * @param userProfile the user profile
     * @return the staff builder
     */
    IStaffBuilder withUserProfile(UserProfile userProfile);

    /**
     * With full name staff builder.
     *
     * @param fullname the fullname
     * @return the staff builder
     */
    IStaffBuilder withFullName(FullName fullname);

    /**
     * With address staff builder.
     *
     * @param address the address
     * @return the staff builder
     */
    IStaffBuilder withAddress(Address address);

    /**
     * With phone numbers staff optional field builder.
     *
     * @param phoneNumbers the phone numbers
     * @return the staff optional field builder
     */
    IStaffOptionalFieldBuilder withPhoneNumbers(Set<PhoneNumber> phoneNumbers);
}
