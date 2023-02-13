package com.example.crud.web.service;


import com.example.crud.web.dao.RoleDao;
import com.example.crud.web.dao.UserDao;
import com.example.crud.web.model.Role;
import com.example.crud.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    private RoleDao roleDao;


    @Autowired
    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder, RoleDao roleDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }






    @Override
    @Transactional
    public void add(User user) {
        Set<Role> roleSet = new HashSet<>();
        for(Role r : user.getRoles()) {
            Role exist = roleDao.getByFullName(r.getName());
            if (exist != null) {
                roleSet.add(exist);
            } else {
                roleSet.add(r);
            }
        }
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getNotEncodePass()));
        userDao.add(user);
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void edit(User user) {
        Set<Role> roleSet = new HashSet<>();
        for(Role r : user.getRoles()) {
            Role exist = roleDao.getByFullName(r.getName());
            if (exist != null) {
                roleSet.add(exist);
            } else {
                roleSet.add(r);
            }
        }
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getNotEncodePass()));
        userDao.edit(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDao.getUsers();
    }


    @Override
    public User findUserByNamelogin(String login) {
        return userDao.findUserByNamelogin(login);
    }

    @Override
    @Transactional
    public void addRole(User user, Role role) {
        userDao.addRole(user, role);
    }



}
