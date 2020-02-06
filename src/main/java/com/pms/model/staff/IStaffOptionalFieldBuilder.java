package com.pms.model.staff;

/**
 * The interface Staff optional field builder.
 */
public interface IStaffOptionalFieldBuilder {

    /**
     * With id staff optional field builder.
     *
     * @param id the id
     * @return the staff optional field builder
     */
    IStaffOptionalFieldBuilder withId(Long id);

    /**
     * Build staff.
     *
     * @return the staff
     */
    Staff build();
}
