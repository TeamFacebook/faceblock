package pl.sda.fencebook.user.model;

import java.util.Arrays;

public enum Sex {
    M("M"), F("F");

    private String value;

    private Sex(String value){
        this.value=value;
    }

    public static Sex fromValue(String value){
        for (Sex s : values()){
            if(s.value.equalsIgnoreCase(value)){
                return s;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type "+value+", Allowed values are "+ Arrays.toString(values())+", Defaulting to Male."
        );
    }
}
