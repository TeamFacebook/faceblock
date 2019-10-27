package pl.sda.fencebook.invitation.model;

import java.util.Arrays;

public enum InvitationResponse {
    ACCEPT("accept"), REFUSE("refuse");

    private String value;

    InvitationResponse(String value){
        this.value=value;
    }

    public static InvitationResponse fromValue(String value){
        for (InvitationResponse r : values()){
            if(r.value.equalsIgnoreCase(value)){
                return r;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type "+value+", Allowed values are "+ Arrays.toString(values())+"."
        );
    }
}
