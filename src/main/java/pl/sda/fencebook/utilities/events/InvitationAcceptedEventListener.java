package pl.sda.fencebook.utilities.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.sda.fencebook.user.model.Notification;
import pl.sda.fencebook.user.service.UserService;

@Component
public class InvitationAcceptedEventListener implements ApplicationListener<InvitationAcceptedEvent> {
    private final UserService userService;

    @Autowired
    public InvitationAcceptedEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(InvitationAcceptedEvent invitationAcceptedEvent) {
        Notification notification = new Notification();
        notification.setUserId(invitationAcceptedEvent.getToWhom());
        notification.setMessage(invitationAcceptedEvent.getMessage());
        userService.sendNewNotificationToUser(invitationAcceptedEvent.getToWhom(), notification);
    }
}
