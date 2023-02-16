package com.example.crud.web.dao;

import com.example.crud.web.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImp implements RoleDao{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getById(Long id) {
        Role role = entityManager.find(Role.class, id);
        return role;
    }

    @Override
    public Role getByShortName(String name) {
        try {
            return entityManager.createQuery("Select r from Role r where r.name = :name", Role.class)
                    .setParameter("name","ROLE_" + name).getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    };

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("Select distinct r from Role r join fetch r.users",Role.class).getResultList();
    }

    @Override
    public Role getByFullName(String name) {
         try {
            return  entityManager.createQuery("Select r from Role r where r.name = :name", Role.class)
                    .setParameter("name",name).getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
             return null;
         }
    }

    @Override
    public void add(Role r) {
        entityManager.merge(r);
    }

}
