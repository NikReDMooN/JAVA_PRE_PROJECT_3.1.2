package com.example.crud.web.service;

import com.example.crud.web.model.Role;

import java.util.List;


public interface RoleService {

    public Role getById(Long id);

    public Role getByShortName(String name);

    public Role getByFullName(String name);


    public List<Role> getRoles();

}
