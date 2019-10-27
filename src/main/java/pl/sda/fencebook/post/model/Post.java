package pl.sda.fencebook.post.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.fencebook.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Date date;
    @NotNull
    private String title, text;
    private Reaction reaction;
    private Integer reactionAuthorId;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    @JsonIgnore
    private User author;
    @NotNull
    private Boolean isActive = Boolean.TRUE;
}
