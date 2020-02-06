package com.pms.service;

import com.pms.dao.AdminDao;
import com.pms.model.admin.Admin;
import com.pms.model.admin.AdminBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Admin service.
 */
@Service
public class AdminService {

    private AdminDao adminDao;

    /**
     * Instantiates a new Admin service.
     *
     * @param adminDao the admin dao
     */
    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public Iterable<Admin> getAll() {
        return adminDao.findAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<Admin> getById(Long id) {
        return adminDao.findById(id);
    }

    /**
     * Create optional.
     *
     * @param admin the admin
     * @return the optional
     */
    public Optional<Admin> create(Admin admin) {
        Admin adminToAdd = getBuild(admin, admin.getId());

        return Optional.of(adminDao.save(adminToAdd));
    }

    /**
     * Update optional.
     *
     * @param admin the admin
     * @param id    the id
     * @return the optional
     */
    public Optional<Admin> update(Admin admin, Long id) {
        Admin adminToAdd = getBuild(admin, id);

        return Optional.of(adminDao.save(adminToAdd));
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(Long id) {
        adminDao.deleteById(id);
    }

    private Admin getBuild(Admin admin, Long id) {
        return AdminBuilder
                .admin()
                .withUserProfile(admin.getUserProfile())
                .withFullName(admin.getFullName())
                .withId(id)
                .build();
    }
}
