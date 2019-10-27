package pl.sda.fencebook.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.sda.fencebook.post.model.*;
import pl.sda.fencebook.user.model.User;
import pl.sda.fencebook.user.service.UserService;
import pl.sda.fencebook.utilities.events.ReactionNotificationEvent;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    private final UserService userService;
    private final PostRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public PostService(UserService userService, PostRepository repository, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.repository = repository;
        this.eventPublisher = publisher;
    }

    public void generateNewPost(NewPostRequest request){
        Post post = new Post();
        post.setAuthor(userService.getUserById(request.getAuthorId()));
        post.setDate(new Date());
        post.setTitle(request.getTitle());
        post.setText(request.getText());
        repository.save(post);
        userService.sendEmailToUser(request.getAuthorId(), request.getTitle(), request.getText());
    }

    public void removePost(RemovePostRequest request){
        Post post = repository.findById(request.getPostId()).get();
        post.setIsActive(Boolean.FALSE);
        repository.save(post);
    }

    public List<Post> getUserPosts(Integer id){
        User user = userService.getUserById(id);
        return user.getPosts();
    }

    public void addReactionToPost(AddReactionRequest request){
        Post post = repository.findById(request.getPostId()).get();
        Reaction reaction = Reaction.LIKE;
        for (Reaction r : Reaction.values()){
            if(r.ordinal() == request.getReactionId()){
                reaction=r;
            }
        }
        post.setReaction(reaction);
        post.setReactionAuthorId(request.getAuthorId());
        repository.save(post);
        publishReactionEvent(post.getReactionAuthorId(), post.getAuthor().getId(), reaction.name());
    }

    private void publishReactionEvent(Integer who, Integer toWhom, String message){
        ReactionNotificationEvent event = new ReactionNotificationEvent(this, who, toWhom, message);
        eventPublisher.publishEvent(event);
    }
}
