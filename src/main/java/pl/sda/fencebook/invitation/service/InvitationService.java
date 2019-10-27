package pl.sda.fencebook.invitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fencebook.invitation.model.*;
import pl.sda.fencebook.user.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class InvitationService {
    private final InvitationRepository repository;
    private final UserService userService;

    @Autowired
    public InvitationService(InvitationRepository repository, UserService userService) {
        this.userService = userService;
        this.repository = repository;
    }

    public void sendInvitationTo(SendIvitationRequest request){
        Invitation invitation = new Invitation();
        invitation.setInvitationSender(userService.getUserById(request.getSenderId()));
        invitation.setInvitationReciever(userService.getUserById(request.getRecieverId()));
        repository.save(invitation);
    }

    public void respondToInvitation(InvitationResponseRequest request){
        Invitation invitation = repository.findById(request.getInvitationId()).get();
        InvitationResponse response = InvitationResponse.fromValue(request.getResponse());
        if (response.equals(InvitationResponse.ACCEPT)){
            userService.addFriendForUser(invitation.getInvitationReciever(), invitation.getInvitationSender());
            repository.delete(invitation);
        }
        if (response.equals(InvitationResponse.REFUSE)){
            repository.delete(invitation);
        }
    }

    public List<Invitation> getAllIvitations(){
        return repository.findAll();
    }
}
