package com.pms.model.staff;

import com.pms.model.Address;
import com.pms.model.FullName;
import com.pms.model.PhoneNumber;
import com.pms.model.userprofile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class StaffBuilder implements IStaffBuilder, IStaffOptionalFieldBuilder {

    private List<Consumer<Staff>> operations;

    private static StaffBuilder INSTANCE;

    public static StaffBuilder staff() {
        if (INSTANCE == null) {
            INSTANCE = new StaffBuilder();
        }

        return INSTANCE;
    }

    private StaffBuilder() {
        this.operations = new ArrayList<>();
    }

    @Override
    public IStaffBuilder withUserProfile(UserProfile userProfile) {
        operations.add(staff -> staff.setUserProfile(userProfile));
        return this;
    }

    @Override
    public IStaffBuilder withFullName(FullName fullname) {
        operations.add(staff -> staff.setFullName(fullname));
        return this;
    }

    @Override
    public IStaffBuilder withAddress(Address address) {
        operations.add(staff -> staff.setAddress(address));
        return this;
    }

    @Override
    public IStaffOptionalFieldBuilder withPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        operations.add(staff -> staff.setPhoneNumbers(phoneNumbers));
        return this;
    }

    @Override
    public IStaffOptionalFieldBuilder withId(Long id) {
        operations.add(userProfile -> userProfile.setId(id));
        return this;
    }

    @Override
    public Staff build() {
        Staff staff = new Staff();
        operations.forEach(operation -> operation.accept(staff));
        return staff;
    }
}
