package com.example.crud.web.service;

import com.example.crud.web.model.Role;


public interface RoleService {

    public Role getById(Long id);

    public Role getByShortName(String name);

    public Role getByFullName(String name);
}
