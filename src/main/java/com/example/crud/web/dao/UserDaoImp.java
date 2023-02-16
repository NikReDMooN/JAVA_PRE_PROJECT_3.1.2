package com.example.crud.web.dao;


import com.example.crud.web.model.Role;
import com.example.crud.web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Override
    public void add(User user) {
        entityManager.merge(user);
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void delete(User user1) {
        User  user = entityManager.find(User.class, user1.getId());
        user.setRoles(null);
        entityManager.merge(user);
        entityManager.remove(user);
    }

    @Override
    public User getById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("Select distinct u from User u join fetch u.roles").getResultList();
    }

    @Override
    public User findUserByNamelogin(String login){
         try{
             return entityManager.createQuery("SELECT u from User  u  join fetch u.roles where u.login = :login", User.class).setParameter("login", login).getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
             return null;
         }
    }

    @Override
    public void addRole(User user, Role role) {
        user.getRoles().add(role);
        entityManager.merge(user);
    }


}
