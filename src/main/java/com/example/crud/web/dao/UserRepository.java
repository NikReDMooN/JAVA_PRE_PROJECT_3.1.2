package com.example.crud.web.dao;

import com.example.crud.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);//JPA отбрасывает findBy и ищет поле username в сущности
}