package com.marcin.Facebook.controller;

import com.marcin.Facebook.model.RegisterUserRequest;
import com.marcin.Facebook.model.User;
import com.marcin.Facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/register")
@RestController
public class RegisterController {

    private final UserService service;

    @Autowired
    public RegisterController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody RegisterUserRequest request) {
        service.createUser(request);
    }

    @GetMapping
    public List<User> getUsersList() {
        return service.retriveUsers();
    }
}
