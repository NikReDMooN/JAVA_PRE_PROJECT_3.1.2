package com.example.crud.web.controller;

import com.example.crud.web.model.Role;
import com.example.crud.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import com.example.crud.web.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String home(@RequestParam String login, @AuthenticationPrincipal User log, Model model) {
        if (!log.getLogin().equals(login)) {
            return "user/error";
        }
        User user = userService.findUserByNamelogin(login);
        model.addAttribute("user", user);
        return "bootstrap/user";
    }



}
