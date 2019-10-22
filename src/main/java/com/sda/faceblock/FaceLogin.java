package com.sda.faceblock;

import java.util.List;

public class FaceLogin {

    public static boolean faceLogin(List<Profile> profileList, String login, String password) {

        if(EmailCheck.emailCheck(profileList, login)) {

            for(Profile p : profileList) {
                if(p.getEmail().equals(login)) {
                    if(p.getPassword().equals(password)) {
                        return true;
                    } else System.out.println("Złe hasło");
                } else System.out.println("Brak uzytkownika");
            }
        }
        return false;
    }
}
