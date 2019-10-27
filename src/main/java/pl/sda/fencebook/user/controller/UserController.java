package pl.sda.fencebook.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fencebook.user.model.*;
import pl.sda.fencebook.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public String createUser(@RequestBody CreateUserRequest request){
        return service.createUser(request);
    }

    @GetMapping
    public List<User> getUsers(){
        return service.retrieveUsers();
    }

    @GetMapping(value = "/login")
    public boolean tryLogIn(@RequestBody LogInRequest request){
        return service.logIn(request);
    }

    @GetMapping(value = "/getUser")
    public User getUser(@RequestBody LogInRequest request){
        return service.retrieveUserProfile(request);
    }

    @PostMapping(value = "/tenderProfile")
    public TenderUser getTenderProfile(@RequestBody GetTenderUserByIdRequest request){
        return service.getTenderUser(request);
    }

    @GetMapping(path = "/{id}")
    public List<Notification> getNotifications(@PathVariable(value = "id") Integer id){
        return getNotifications(id);
    }
}
