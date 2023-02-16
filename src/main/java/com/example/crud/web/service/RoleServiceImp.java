package com.example.crud.web.service;


import com.example.crud.web.dao.RoleDao;
import com.example.crud.web.dao.RoleDaoImp;
import com.example.crud.web.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDaoImp roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Override
    public Role getByShortName(String name) {
        return roleDao.getByShortName(name);
    }

    @Override
    public Role getByFullName(String name) {
        return roleDao.getByFullName(name);
    }
}
