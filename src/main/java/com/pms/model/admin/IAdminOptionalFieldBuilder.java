package com.pms.model.admin;

/**
 * The interface Admin optional field builder.
 */
public interface IAdminOptionalFieldBuilder {

    /**
     * With id admin optional field builder.
     *
     * @param id the id
     * @return the admin optional field builder
     */
    IAdminOptionalFieldBuilder withId(Long id);

    /**
     * Build admin.
     *
     * @return the admin
     */
    Admin build();
}
