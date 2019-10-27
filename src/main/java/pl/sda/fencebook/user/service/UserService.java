package pl.sda.fencebook.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sda.fencebook.user.model.CreateUserRequest;
import pl.sda.fencebook.user.model.*;
import pl.sda.fencebook.user.model.UserRepository;
import pl.sda.fencebook.utilities.PasswordGenerator;
import pl.sda.fencebook.utilities.events.InvitationAcceptedEvent;
import pl.sda.fencebook.utilities.events.NotificationsReadEvent;
import pl.sda.fencebook.utilities.service.EmailService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repository;
    private final EmailService emailService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserService(UserRepository repository, EmailService emailService, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.emailService = emailService;
        this.eventPublisher = eventPublisher;
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

    public void sendEmailToUser(Integer userId, String subject, String text){
        String email = repository.findById(userId).get().getEmail();
        emailService.sendTestMessage(email, subject, text);
    }

    public void addFriendForUser(User user, User newFriend){
        user.getFriends().add(newFriend);
        newFriend.getFriends().add(user);
        repository.save(user);
        repository.save(newFriend);
        String message = user.getName()+" "+user.getLastname()+" accepted your friend invitation.";
        publishFriendAcceptedEvent(user.getId(), newFriend.getId(), message);
    }

    private void publishFriendAcceptedEvent(Integer who, Integer toWhom, String message){
        InvitationAcceptedEvent event = new InvitationAcceptedEvent(this, who, toWhom, message);
        eventPublisher.publishEvent(event);
    }

    public void sendNewNotificationToUser(Integer userId, Notification notification){
        User user = repository.findById(userId).get();
        user.getNotifications().add(notification);
        repository.save(user);
    }

    public List<Notification> readNotifications(Integer userId){
        User user = getUserById(userId);
        List<Notification> notifications = user.getNotifications().stream().collect(Collectors.toList());
        //Collections.copy(notifications, user.getNotifications());
        publishNotificationReadEvent(userId);
        return notifications;
    }

    private void publishNotificationReadEvent(Integer userId){
        NotificationsReadEvent event = new NotificationsReadEvent(this, userId);
        eventPublisher.publishEvent(event);
    }
}
