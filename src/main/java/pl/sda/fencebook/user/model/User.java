package pl.sda.fencebook.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.fencebook.invitation.model.Invitation;
import pl.sda.fencebook.post.model.Post;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name, lastname;
    private Sex sex;
    @Email
    private String email;
    private String password;
    @OneToMany(mappedBy = "author")
    private List<Post> posts;
    @ManyToMany
    @JsonIgnore
    private List<User> friends;
    @OneToMany(mappedBy = "invitationReciever")
    private List<Invitation> pendingInvitations;
}
