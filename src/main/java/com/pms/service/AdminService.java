package com.pms.service;

import com.pms.dao.AdminDao;
import com.pms.model.admin.Admin;
import com.pms.model.admin.AdminBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private AdminDao adminDao;

    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public Iterable<Admin> getAll() {
        return adminDao.findAll();
    }

    public Optional<Admin> getById(Long id) {
        return adminDao.findById(id);
    }

    public Optional<Admin> create(Admin admin) {
        Admin adminToAdd = getBuild(admin, admin.getId());

        return Optional.of(adminDao.save(adminToAdd));
    }

    public Optional<Admin> update(Admin admin, Long id) {
        Admin adminToAdd = getBuild(admin, id);

        return Optional.of(adminDao.save(adminToAdd));
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
