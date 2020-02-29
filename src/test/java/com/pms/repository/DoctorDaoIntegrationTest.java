package com.pms.repository;

import com.pms.dao.DoctorDao;
import com.pms.model.staff.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DoctorDaoIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DoctorDao doctorDao;

    @Test
    public void whenFindById_thenReturnDoctor() {
        // given
        Doctor doctor = new Doctor();
//        doctor.setUserProfile();
    }
}
