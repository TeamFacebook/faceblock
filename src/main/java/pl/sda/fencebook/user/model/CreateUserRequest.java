package pl.sda.fencebook.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class CreateUserRequest {
    @NotNull
    private String name, lastname;
    @NotNull
    private String sex;
    @Email
    @NotNull
    private String email;

    public CreateUserRequest(@NotNull String name, @NotNull String lastname, @NotNull String sex, @Email @NotNull String email) {
        this.name = name;
        this.lastname = lastname;
        this.sex = sex;
        this.email = email;
    }
}
