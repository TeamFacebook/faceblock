package pl.sda.fencebook.invitation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class SendIvitationRequest {
    @NotNull
    private Integer recieverId;
    @NotNull
    private Integer senderId;
}
