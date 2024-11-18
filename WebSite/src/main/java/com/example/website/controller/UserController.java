package com.example.website.controller;

import com.example.website.model.UserModel;
import com.example.website.repository.UserRepository;
import com.example.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/register", consumes = "aaplication/json")
    public UserModel register(UserModel user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
