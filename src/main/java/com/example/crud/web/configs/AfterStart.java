package com.example.crud.web.configs;


import com.example.crud.web.model.Role;
import com.example.crud.web.model.User;
import com.example.crud.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class AfterStart {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AfterStart(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
       if(userService.findUserByNamelogin("root").isEmpty()) {
           System.out.println("------------------------------STARTED TO CREATING ADMIN--------------");
           Role role = new Role();
           role.setName("ROLE_ADMIN");
           Role role1 = new Role();
           role1.setName("ROLE_USER");
           User user = new User();
           user.setFirstName("Nikita");
           user.setLastName("Pisarenko");
           user.setEmail("nikita@gmail.com");
           user.setLogin("root");
           user.setPassword(passwordEncoder.encode("root"));
           user.setRoles(Set.of(role, role1));
           userService.add(user);
           System.out.println("------------------------------ADMIN HAS BEEN CREATED--------------");
           System.out.println("------------------------------LOGIN = root PASSWORD = root-----------------------------");
           System.out.println("------------------------------STARTED TO CREATING USER--------------");
           User user1 = new User();
           user1.setFirstName("Vlad");
           user1.setLastName("Beletsky");
           user1.setEmail("vlad@mail.com");
           user1.setLogin("user");
           user1.setPassword(passwordEncoder.encode("user"));
           user1.setRoles(Set.of(role1));
           userService.add(user1);
           System.out.println("------------------------------USER HAS BEEN CREATED--------------");
           System.out.println("------------------------------LOGIN = user PASSWORD = user-------------------------");
       }

    }
}
