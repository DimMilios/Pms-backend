package com.pms.dao;

import com.pms.model.Inpatient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InpatientDao extends CrudRepository<Inpatient, Long> {

    Optional<Inpatient> findBySsn(Long ssn);
}
