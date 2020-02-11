package com.pms.dao;

import com.pms.model.staff.Doctor;
import com.pms.model.staff.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * The interface Doctor dao.
 */
@Repository
public interface DoctorDao extends CrudRepository<Doctor, Long> {

    /**
     * Find by user profile username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Doctor> findByUserProfileUsername(String username);

    @Query(value = "SELECT DISTINCT * FROM STAFF s\n" +
            "WHERE s.staff_type='Doctor' \n" +
            "AND NOT EXISTS (\n" +
            "  SELECT *\n" +
            "  FROM appointments a\n" +
            "  WHERE a.appointment_date=:date AND a.appointment_time=:time \n" +
            "  AND s.id = a.doctor_id\n" +
            ")", nativeQuery = true)
    List<Doctor> findAvailableForAppointment(LocalDate date, LocalTime time);

//    "SELECT DISTINCT doctors FROM staff s\n" +
//            "WHERE NOT EXISTS (\n" +
//            "  SELECT appointments \n" +
//            "  FROM appointments a\n" +
//            "  WHERE a.appointmentDate = :date AND a.appointmentTime = :time \n" +
//            "  AND s.id = a.doctor.id \n" +
//            ")"
}
