package com.pms.dao;

import com.pms.model.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends CrudRepository<Staff, Long> {
}
