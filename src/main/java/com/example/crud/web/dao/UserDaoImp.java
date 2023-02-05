package com.example.crud.web.dao;

import org.springframework.stereotype.Repository;
import com.example.crud.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

    @Override
    public void add(User user) {
        entityManager.merge(user);
     //   entityManager.getTransaction().commit();
    }

    @PersistenceContext
    private EntityManager entityManager;

/*    @PersistenceContext
    public UserDaoImp (EntityManager entityManager) {
        this.entityManager = entityManager;
    }*/

    @Override
    public void delete(Long id) {
        User  user = entityManager.find(User.class, id);
        entityManager.remove(user);
/*        entityManager.getTransaction().commit();*/

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
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        return entityManager.createQuery(criteria).getResultList();
    }

}
