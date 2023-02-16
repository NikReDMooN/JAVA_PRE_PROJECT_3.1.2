package com.example.crud.web.dao;

import com.example.crud.web.model.Role;

import java.util.List;

public interface RoleDao {

    public Role getById(Long id);

    public Role getByShortName(String name);

    public Role getByFullName(String name);

    public void add(Role r);

    public List<Role> getRoles();

}
