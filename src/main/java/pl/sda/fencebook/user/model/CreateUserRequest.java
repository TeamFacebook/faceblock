package pl.sda.fencebook.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CreateUserRequest {
    @NotNull
    private String name, lastname;
    @NotNull
    private String sex;
    @Email
    @NotNull
    private String email;
}
