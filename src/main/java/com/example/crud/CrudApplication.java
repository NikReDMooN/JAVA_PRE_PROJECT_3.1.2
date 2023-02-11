package com.example.crud;

import com.example.crud.web.dao.UserDao;
import com.example.crud.web.model.Role;
import com.example.crud.web.model.User;
import com.example.crud.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {

        SpringApplication.run(CrudApplication.class, args);
    }

}
