package com.example.crud.web.service;


import com.example.crud.web.dao.RoleDao;
import com.example.crud.web.model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

    private RoleDao roleDao;

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }
}
