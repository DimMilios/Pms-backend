package com.pms.dao;

import com.pms.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDao extends CrudRepository<Doctor, Long> {
}
