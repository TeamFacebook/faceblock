package com.marcin.Facebook.controller;

import com.marcin.Facebook.model.LoginUserRequest;
import com.marcin.Facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/login")
@RestController
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String validateUser(@RequestBody LoginUserRequest request) {
        return userService.validateUser(request);
    }
}
