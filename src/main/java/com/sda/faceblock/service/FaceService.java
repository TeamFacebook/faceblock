package com.sda.faceblock.service;

import com.sda.faceblock.models.Profile;
import com.sda.faceblock.utils.EmailCheck;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FaceService {

    private final List<Profile> profileList = new ArrayList<>();

    public boolean regUser(Profile profileReq) {
        for (Profile p: profileList) {
            if( p.getEmail().equals(profileReq.getEmail()))
                return false;
        }
        profileList.add(profileReq);
        return true;
    }

    public List<Profile> showList() {
        return profileList;
    }

    public String generateRandomPassword() {

        List rules = Arrays.asList(
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1));

        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generatePassword(8, rules);
        return password;
    }

    public boolean login(String login, String password) {

        if(EmailCheck.emailCheck(profileList, login))
            return true;
        return false;
    }
}