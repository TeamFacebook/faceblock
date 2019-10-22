package pl.sda.fencebook.utilities;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static SecureRandom random = new SecureRandom();

    //dictionaries used for password generation
    public static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMERIC = "0123456789";
    public static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

    public String generatePassword(int length, String dictionary){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i<length; i++){
            result.append(dictionary.charAt(random.nextInt(dictionary.length())));
        }
        return result.toString();
    }
}
