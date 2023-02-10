package com.example.crud.web.dao;

import com.example.crud.web.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImp implements RoleDao{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getById(Long id) {
        Role role = entityManager.find(Role.class, id);
        return role;
    }
}
