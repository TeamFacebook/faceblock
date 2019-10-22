package com.sda.faceblock;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileReq {

    private String name;
    private String surname;
    private Sex sex;
    private String email;
    private String password;

}
