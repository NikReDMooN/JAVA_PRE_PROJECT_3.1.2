package com.example.crud.web.controllers;

import com.example.crud.web.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userApi")
public class RestControllerUser {

    @GetMapping("/auth")
    public ResponseEntity<User> getAuthUser(@AuthenticationPrincipal User user) {

        System.out.println("------------------------------------------------- USERAPI ------------------------");
        return ResponseEntity.ok(user);
    }
}
