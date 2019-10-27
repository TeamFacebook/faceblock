package pl.sda.fencebook.invitation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fencebook.invitation.model.Invitation;
import pl.sda.fencebook.invitation.model.InvitationResponse;
import pl.sda.fencebook.invitation.model.InvitationResponseRequest;
import pl.sda.fencebook.invitation.model.SendIvitationRequest;
import pl.sda.fencebook.invitation.service.InvitationService;

import java.util.List;

@RestController
@RequestMapping("/user/invitation")
public class InvitationController {
    private InvitationService service;

    @Autowired
    public InvitationController(InvitationService service) {
        this.service = service;
    }

    @PostMapping
    public void sendInvitationTo(@RequestBody SendIvitationRequest request){
        service.sendInvitationTo(request);
    }

    @GetMapping
    public List<Invitation> getAllInvitations(){
        return service.getAllIvitations();
    }

    @PostMapping(value = "/respond")
    public void respondToInvitation(@RequestBody InvitationResponseRequest request){
        service.respondToInvitation(request);
    }
}
