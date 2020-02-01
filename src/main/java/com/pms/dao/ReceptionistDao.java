package com.pms.dao;

import com.pms.model.staff.Receptionist;
import org.springframework.data.repository.CrudRepository;

public interface ReceptionistDao extends CrudRepository<Receptionist, Long> {
}
