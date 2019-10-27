package pl.sda.fencebook.invitation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class InvitationResponseRequest {
    @NotNull
    private Integer invitationId;
    @NotNull
    private String response;
}
