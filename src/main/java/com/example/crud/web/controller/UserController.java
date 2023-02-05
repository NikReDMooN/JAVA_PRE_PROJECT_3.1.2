package com.example.crud.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.crud.web.model.User;
import com.example.crud.web.service.UserService;

import java.util.List;

@Controller
public class UserController {


        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }


    @GetMapping("/getAllUsers")
    public String home(Model model) {
        List<User> listUsers = userService.getUsers();
        model.addAttribute("tableList", listUsers);
        return "startPage";
    }

        @GetMapping(value = "/new")
        public String printNewUserPage(User user) {
            return "newUser";
        }

        @PostMapping(value = "/new")
        public String saveUser( User user) {
            if (user.getEmail().equals("") || user.getFirstName().equals("") || user.getLastName().equals("")) {
                return "newUserBadData";
            }
            userService.add(user);
            return "redirect:/start";
        }

        @GetMapping("/delete")
        public String deleteUser(@RequestParam Long id){
            userService.delete(id);
            return "redirect:/start";
        }

        @GetMapping("/edit")
        public String printEditPage(@RequestParam Long id, Model model) {
            model.addAttribute("user", userService.getById(id));

            return"appdateUser";
        }

        @PostMapping(value = "/edit")
        public String editUser( User user) {
            if (user.getEmail().equals("") || user.getFirstName().equals("") || user.getLastName().equals("")) {
                return "appdateUserBadData";
            }
            userService.edit(user);
            return "redirect:/start";
        }
}
