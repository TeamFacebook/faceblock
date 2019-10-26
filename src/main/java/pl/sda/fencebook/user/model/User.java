package pl.sda.fencebook.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @OneToMany(mappedBy = "authorId")
    private List<Post> posts;
}
