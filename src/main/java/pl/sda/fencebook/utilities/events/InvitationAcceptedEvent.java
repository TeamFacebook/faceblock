package pl.sda.fencebook.utilities.events;

import org.springframework.context.ApplicationEvent;

public class InvitationAcceptedEvent extends ApplicationEvent {
    private Integer who, toWhom;
    private String message;

    public InvitationAcceptedEvent(Object source, Integer who, Integer toWhom, String message) {
        super(source);
        this.who = who;
        this.toWhom = toWhom;
        this.message = message;
    }

    public Integer getWho() {
        return who;
    }

    public Integer getToWhom() {
        return toWhom;
    }

    public String getMessage() {
        return message;
    }
}
