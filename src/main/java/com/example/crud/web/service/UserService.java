package com.example.crud.web.service;


import com.example.crud.web.model.Role;
import com.example.crud.web.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService {


    public void add(User user);

    public void delete(Long id);

    public void edit(User user);

    public User getById(Long id);

    public List<User> getUsers();

    public List<User> findUserByNamelogin(String login);

    public void addRole(User user, Role role);

}
