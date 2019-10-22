package com.marcin.Facebook.service;

import com.marcin.Facebook.model.LoginUserRequest;
import com.marcin.Facebook.model.User;
import com.marcin.Facebook.model.RegisterUserRequest;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    private AtomicInteger counter = new AtomicInteger(0);
    private List<User> users = new ArrayList<>();
    private static final String CHAR_LIST =
            "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private User userToValidate;

    public void createUser(RegisterUserRequest request) {
        String password = getRandomPassword(4);
        User user = new User(counter.getAndIncrement(),
                request.getName(),
                request.getLastName(),
                request.getSex(),
                password,
                request.getEmail());
        users.add(user);
    }

    public String getRandomPassword(Integer passwordLength) {
        StringBuffer randomPassword = new StringBuffer(4);
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < passwordLength; i++)
            randomPassword.append(CHAR_LIST.charAt(secureRandom.nextInt(CHAR_LIST.length())));
        return randomPassword.toString();
    }

    public List<User> retriveUsers() {
        return Collections.unmodifiableList(users);
    }

    public String validateUser(LoginUserRequest request) {
        userToValidate = users.stream().filter(c -> c.getEmail().equals(request.getEmail())).findAny().get();
        if (userToValidate.getPassword().equals(request.getPassword())) {
            return "Logged in";
        }
        return "Incorrect password or email";
    }
}

