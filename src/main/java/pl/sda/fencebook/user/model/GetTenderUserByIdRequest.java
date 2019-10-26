package pl.sda.fencebook.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class GetTenderUserByIdRequest {
    private Integer id;
}
