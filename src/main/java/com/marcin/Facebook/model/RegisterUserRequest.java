package com.marcin.Facebook.model;

import com.marcin.Facebook.model.ENUM.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class RegisterUserRequest {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private Sex sex;

    @NotNull
    private String email;
}
