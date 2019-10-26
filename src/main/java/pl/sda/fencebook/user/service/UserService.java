package pl.sda.fencebook.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sda.fencebook.user.model.CreateUserRequest;
import pl.sda.fencebook.user.model.*;
import pl.sda.fencebook.user.model.UserRepository;
import pl.sda.fencebook.utilities.PasswordGenerator;
import pl.sda.fencebook.utilities.service.EmailService;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    private static PasswordGenerator gen = new PasswordGenerator();

    public String createUser(CreateUserRequest request){
        if (repository.findAll().stream().anyMatch(u -> u.getEmail().equals(request.getEmail()))){
            return "Email already taken";
        } else {
            User user = new User();
            user.setName(request.getName());
            user.setLastname(request.getLastname());
            try{
                user.setSex(Sex.fromValue(request.getSex()));
            } catch (IllegalArgumentException e){
                e.printStackTrace();
                user.setSex(Sex.M);
            }
            user.setEmail(request.getEmail());
            user.setPassword(gen.generatePassword(10, PasswordGenerator.ALPHA_CAPS + PasswordGenerator.ALPHA + PasswordGenerator.NUMERIC));
            repository.save(user);
            return user.getPassword();
        }

    }

    public List<User> retrieveUsers() {
        return repository.findAll();
    }

    public boolean logIn(LogInRequest request){
        List<User> users = repository.findAll();
        for(User u : users){
            if (u.getEmail().equals(request.getEmail())){
                if(u.getPassword().equals(request.getPassword())){
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public User retrieveUserProfile(LogInRequest request){
        List<User> users = repository.findAll();
        for (User u : users){
            if (u.getEmail().equals(request.getEmail())){
                if (u.getPassword().equals(request.getPassword())){
                    return u;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public TenderUser getTenderUser(GetTenderUserByIdRequest request){
        RestTemplate restTemplate = new RestTemplate();
        String path = "http://localhost.8080/user";
        UriComponentsBuilder builder =UriComponentsBuilder.fromUriString(path)
                .queryParam("id", request.getId());
        TenderUser user = restTemplate.getForObject(builder.toUriString(), TenderUser.class);

        assert user != null;
        return user;
    }

    public User getUserById(Integer id){
        return repository.findById(id).get();
    }
}
