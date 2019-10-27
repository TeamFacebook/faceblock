package pl.sda.fencebook.utilities.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.sda.fencebook.user.service.UserService;

@Component
public class NotificationsReadEventListener implements ApplicationListener<NotificationsReadEvent> {
    private final UserService userService;

    public NotificationsReadEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(NotificationsReadEvent notificationsReadEvent) {
        userService.getUserById(notificationsReadEvent.getUserId()).getNotifications().forEach(n -> n.setIsActive(false));
    }
}
