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
        return "user/userInfo";
    }






    @GetMapping("/getAllUsers")
    public String home(Model model) {
        List<User> listUsers = userService.getUsers();
        model.addAttribute("tableList", listUsers);
        return "allUsersInfo";
    }

    @GetMapping(value = "/new")
    public String printNewUserPage(User user) {
        return "admin/newUser";
    }

    @PostMapping(value = "/new")
    public String saveUser( User user) {
        if (user.getEmail().equals("") || user.getFirstName().equals("") || user.getLastName().equals("")
        || (user.getRoles() == null) || user.getLogin().equals("") || user.getNotEncodePass().equals("")) {
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
        if (user.getEmail().equals("") || user.getFirstName().equals("") || user.getLastName().equals("")
                || (user.getRoles() == null) || user.getLogin().equals("") || user.getNotEncodePass().equals("")) {
            return "admin/appdateUserBadData";
        }
        userService.edit(user);
        return "redirect:/admin/getAllUsers";
    }


}
