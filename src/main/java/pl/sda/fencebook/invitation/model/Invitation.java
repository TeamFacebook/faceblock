package pl.sda.fencebook.invitation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.fencebook.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @ManyToOne
    @JsonIgnore
    private User invitationSender;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "RECIEVER")
    @JsonIgnore
    private User invitationReciever;
}
