package pl.sda.fencebook.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@Getter
public class NewPostRequest {
    @NotNull
    private Date date;
    @NotNull
    private String title, text;
//    @NotNull
//    private Integer authorId;

}
