package pl.sda.fencebook.utilities.events;

import org.springframework.context.ApplicationEvent;

public class NotificationsReadEvent extends ApplicationEvent {
    private Integer userId;
    public NotificationsReadEvent(Object source, Integer userId) {
        super(source);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}
