package com.pms.dao;

import com.pms.model.prescription.Medicine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineDao extends CrudRepository<Medicine, Long> {
}
