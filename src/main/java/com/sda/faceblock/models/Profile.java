package com.sda.faceblock.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Profile {

    private String name;
    private String surname;
    private Sex sex;
    private String email;
    @Setter
    private String password;

}
