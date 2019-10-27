package pl.sda.fencebook.utilities.events;

import org.springframework.context.ApplicationEvent;

public class ReactionNotificationEvent extends ApplicationEvent {
    private Integer who, toWhom;
    private String message;

    public ReactionNotificationEvent(Object source, Integer who, Integer toWhom, String message) {
        super(source);
        this.message = message;
        this.who = who;
        this.toWhom = toWhom;
    }

    public String getMessage(){
        return message;
    }

    public Integer getWho() {
        return who;
    }

    public Integer getToWhom() {
        return toWhom;
    }

}
