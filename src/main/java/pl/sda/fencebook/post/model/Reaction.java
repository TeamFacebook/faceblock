package pl.sda.fencebook.post.model;

import java.util.Arrays;

public enum Reaction {
    LIKE("LIKE"), LOVE("LOVE"), SMILE("SMILE");

    private String value;

    Reaction(String value){
        this.value=value;
    }

    public static Reaction fromValue(String value){
        for (Reaction r : values()){
            if(r.value.equalsIgnoreCase(value)){
                return r;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type "+value+", Allowed values are "+ Arrays.toString(values())+"."
        );
    }
}
