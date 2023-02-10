package com.example.crud.web.controller;


import com.example.crud.web.model.Role;
import com.example.crud.web.model.User;
import com.example.crud.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/getAllUsers")
    public String home(Model model) {
        List<User> listUsers = userService.getUsers();
        model.addAttribute("tableList", listUsers);
        return "admin/startPage";
    }

    @GetMapping(value = "/new")
    public String printNewUserPage(User user) {
        return "admin/newUser";
    }

    @PostMapping(value = "/new")
    public String saveUser( User user) {
        if (user.getEmail().equals("") || user.getFirstName().equals("") || user.getLastName().equals("")) {
            return "admin/newUserBadData";
        }
        for(Role r : user.getRoles())
            System.out.println(r);
        userService.add(user);
        return "redirect:/admin/getAllUsers";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id){
        userService.delete(id);
        return "redirect:/admin/getAllUsers";
    }

    @GetMapping("/edit")
    public String printEditPage(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.getById(id));

        return"admin/appdateUser";
    }

    @PostMapping(value = "/edit")
    public String editUser( User user) {
        if (user.getEmail().equals("") || user.getFirstName().equals("") || user.getLastName().equals("")) {
            return "appdateUserBadData";
        }
        userService.edit(user);
        return "redirect:/admin/getAllUsers";
    }


}
