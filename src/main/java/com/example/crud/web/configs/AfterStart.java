
package com.example.crud.web.configs;


import com.example.crud.web.model.Role;
import com.example.crud.web.model.User;
import com.example.crud.web.service.RoleService;
import com.example.crud.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class AfterStart {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AfterStart(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
       if(userService.getUserByEmail("nikita@gmail.com") == null) {
           System.out.println("------------------------------STARTED TO CREATING ADMIN--------------");
           Role role = new Role();
           role.setName("ROLE_ADMIN");
           Role role1 = new Role();
           role1.setName("ROLE_USER");
           User user = new User();
           user.setEmail("nikita@gmail.com");
           user.setFirstName("Nikita");
           user.setAge(15);
           user.setLastName("Pisarenko");
           user.setNotEncodePass("root");
           user.setRoles(Set.of(role));
           userService.add(user);
           System.out.println("------------------------------ADMIN HAS BEEN CREATED--------------");
           System.out.println("------------------------------LOGIN = root PASSWORD = root-----------------------------");
           System.out.println("------------------------------STARTED TO CREATING USER--------------");
           User user1 = new User();
           user1.setFirstName("Vlad");
           user1.setLastName("Bel");
           user1.setAge(20);
           user1.setEmail("vlad@mail.com");
           user1.setNotEncodePass("user");
           user1.setRoles(Set.of(role1));
           userService.add(user1);
           System.out.println("------------------------------USER HAS BEEN CREATED--------------");
           System.out.println("------------------------------LOGIN = user PASSWORD = user-------------------------");
       }

    }
}

