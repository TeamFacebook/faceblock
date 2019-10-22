package pl.sda.fencebook.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class LogInRequest {
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
}
