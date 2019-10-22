package com.marcin.Facebook.model;

import com.marcin.Facebook.model.ENUM.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private Integer id;

    private String name;

    private String lastName;

    private Sex sex;

    private String password;

    private String email;
}
