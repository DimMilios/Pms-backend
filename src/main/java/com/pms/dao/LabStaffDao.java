package com.pms.dao;

import com.pms.model.staff.LabStaff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabStaffDao extends CrudRepository<LabStaff, Long> {
}
