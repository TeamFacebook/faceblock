package pl.sda.fencebook.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class AddReactionRequest {
    @NotNull
    private Integer postId, reactionId, authorId;
}
