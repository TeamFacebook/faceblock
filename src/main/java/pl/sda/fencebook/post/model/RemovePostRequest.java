package pl.sda.fencebook.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.text.StyledEditorKit;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class RemovePostRequest {
    @NotNull
    private Integer postId;
}
