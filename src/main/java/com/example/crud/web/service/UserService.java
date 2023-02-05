package com.example.crud.web.service;

import com.example.crud.web.model.User;

import java.util.List;

public interface UserService {


    public void add(User user);

    public void delete(Long id);

    public void edit(User user);

    public User getById(Long id);

    public List<User> getUsers();

}
