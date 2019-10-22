package com.sda.faceblock.controller;
import com.sda.faceblock.models.Profile;
import com.sda.faceblock.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/face")
public class FaceController {

    private FaceService faceService;

    @Autowired
    public FaceController(FaceService faceService) {
        this.faceService = faceService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String regUser(@RequestBody @Valid Profile profile) {
        profile.setPassword(faceService.generateRandomPassword());
        if(faceService.regUser(profile)){
            return "Reg successful! Here's your password: " + profile.getPassword();
        }
        return "User already exists";
    }

    @GetMapping
    public List<Profile> regUser() {
        return faceService.showList();
    }

    @GetMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password) {
        if( faceService.login(login, password)) {
            return "Logged in!";
        } else return "Failed";
    }
}
