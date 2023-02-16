package com.example.crud.web.controller;


import com.example.crud.web.model.User;
import com.example.crud.web.service.RoleService;
import com.example.crud.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping("/getAllUsers")
    public String home(Model model, @AuthenticationPrincipal User log) {
        User admin = userService.findUserByNamelogin(log.getLogin());
        List<User> listUsers = userService.getUsers();
        model.addAttribute("tableList", listUsers);
        model.addAttribute("admin", admin);
        model.addAttribute("roles",roleService.getRoles());
        User newuser = new User();
        model.addAttribute("newuser",newuser);
        return "bootstrap/admin/main";
    }

    @GetMapping("/user")
    public String adminUser(Model model, @AuthenticationPrincipal User log ) {
        User user = userService.findUserByNamelogin(log.getLogin());
        model.addAttribute("user", user);
        model.addAttribute("admin", user);
        return "bootstrap/admin/adminUser";
    }


    @PostMapping(value = "/new" )
    public String saveUser( User addUser, Model model, @AuthenticationPrincipal User log) {
        if (addUser.getEmail().equals("") || addUser.getFirstName().equals("") || addUser.getLastName().equals("")) {
            User admin = userService.findUserByNamelogin(log.getLogin());
            List<User> listUsers = userService.getUsers();
            model.addAttribute("tableList", listUsers);
            model.addAttribute("admin", admin);
            model.addAttribute("roles",roleService.getRoles());
            User rebuildUser = new User();
            model.addAttribute("newuser",rebuildUser);
            return "bootstrap/admin/mainNewUserNullData";
        }
        if (userService.findUserByNamelogin(addUser.getLogin()) != null) {
            User admin = userService.findUserByNamelogin(log.getLogin());
            List<User> listUsers = userService.getUsers();
            model.addAttribute("tableList", listUsers);
            model.addAttribute("admin", admin);
            model.addAttribute("roles",roleService.getRoles());
            User rebuildUser = new User();
            model.addAttribute("newuser",rebuildUser);
            return "bootstrap/admin/mainBadLogin";
        }
        System.out.println("new user = "+ addUser);
        System.out.println("password = " + addUser.getPassword());
        userService.add(addUser);
        return "redirect:/admin/getAllUsers";
    }

    @GetMapping("/delete")
    public String deleteUser(User user){
        userService.delete(user);
        return "redirect:/admin/getAllUsers";
    }


    @PostMapping(value = "/edit")
    public String editUser( User user, Model model, @AuthenticationPrincipal User log) {
        System.out.println("id = "  +  user.getId());
        if (user.getEmail().equals("") || user.getFirstName().equals("") || user.getLastName().equals("")) {
            User admin = userService.findUserByNamelogin(log.getLogin());
            List<User> listUsers = userService.getUsers();
            model.addAttribute("tableList", listUsers);
            model.addAttribute("admin", admin);
            model.addAttribute("roles",roleService.getRoles());
            User rebuildUser = new User();
            model.addAttribute("newuser",rebuildUser);
            model.addAttribute("id", user.getId());

            return "bootstrap/admin/mainEditNullData";
        }
        System.out.println("user = " + user);
        userService.edit(user);
        return "redirect:/admin/getAllUsers";
    }


}
