package com.pms.model.admin;

import com.pms.model.FullName;
import com.pms.model.userprofile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AdminBuilder implements IAdminBuilder, IAdminOptionalFieldBuilder {

    private List<Consumer<Admin>> operations;

    private static AdminBuilder INSTANCE;

    public static AdminBuilder admin() {
        if (INSTANCE == null) {
            INSTANCE = new AdminBuilder();
        }

        return INSTANCE;
    }

    private AdminBuilder() {
        this.operations = new ArrayList<>();
    }

    @Override
    public IAdminBuilder withUserProfile(UserProfile userProfile) {
        operations.add(admin -> admin.setUserProfile(userProfile));
        return this;
    }

    @Override
    public IAdminOptionalFieldBuilder withFullName(FullName fullname) {
        operations.add(admin -> admin.setFullName(fullname));
        return this;
    }

    @Override
    public IAdminOptionalFieldBuilder withId(Long id) {
        operations.add(admin -> admin.setId(id));
        return this;
    }

    @Override
    public Admin build() {
        Admin admin = new Admin();
        operations.forEach(operation -> operation.accept(admin));
        return admin;
    }
}
