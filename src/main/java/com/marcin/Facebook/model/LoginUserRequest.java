package com.marcin.Facebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {

    @NotNull
    private String password;

    @NotNull
    private String email;
}