package com.example.crud.web.controller;

import com.example.crud.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.crud.web.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public String home(@RequestParam String login, Model model) {
        List<User> user = userService.findUserByNamelogin(login);
        model.addAttribute("tableList", user.get(0));
        return "user/userInfo";
    }


}
