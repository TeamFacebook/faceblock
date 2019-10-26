package pl.sda.fencebook.user.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TenderUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    @Min(18)
    private Integer age;
    @NotNull
    private Sex sex;
    //preferences
    @NotNull
    private String city;
    @NotNull
    private Sex prefSex;
    @NotNull
    private Integer minAge;
    @NotNull
    private Integer maxAge;
    @Max(250)
    private String description;
    //matches
    @ManyToMany
    private List<User> acceptedBy;
    @ManyToMany
    private List<User> rejected;

    public TenderUser(@NotNull String login, @NotNull String password, @NotNull String name, @NotNull String email, @NotNull @Min(18) Integer age, @NotNull Sex sex, @NotNull String city, @NotNull Sex prefSex, @NotNull Integer minAge, @NotNull Integer maxAge, @Max(250) String description, List<User> acceptedBy, List<User> rejected) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.city = city;
        this.prefSex = prefSex;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.description = description;
        this.acceptedBy = acceptedBy;
        this.rejected = rejected;
    }
}
