package pl.sda.fencebook.utilities.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.sda.fencebook.user.service.UserService;

@Component
public class CustomSpringEventListener implements ApplicationListener<ReactionNotificationEvent> {
    private final UserService userService;

    @Autowired
    public CustomSpringEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ReactionNotificationEvent event) {
        userService.sendEmailToUser(event.getWho(), "Reaction added to post", event.getMessage());
        userService.sendEmailToUser(event.getToWhom(), "Someone reacted to your post", event.getMessage());
    }
}
