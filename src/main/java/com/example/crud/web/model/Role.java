package com.example.crud.web.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private long id;

    @ManyToMany(mappedBy = "roles" )
    private Set<User> users = new HashSet<>();

    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String getAuthority() {
        return this.getName();
    }

    public Role() {
    }


    public Role(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
