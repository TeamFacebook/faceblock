package pl.sda.fencebook.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fencebook.post.model.NewPostRequest;
import pl.sda.fencebook.post.model.Post;
import pl.sda.fencebook.post.model.PostRepository;
import pl.sda.fencebook.user.model.User;
import pl.sda.fencebook.user.service.UserService;
import pl.sda.fencebook.utilities.service.EmailService;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    private final UserService userService;
    private final PostRepository repository;
    private final EmailService emailService;

    @Autowired
    public PostService(UserService userService, PostRepository repository, EmailService emailService) {
        this.userService = userService;
        this.repository = repository;
        this.emailService = emailService;
    }

    public void generateNewPost(Integer userId, NewPostRequest request){
        Post post = new Post();
        post.setAuthorId(userId);
        post.setDate(new Date());
        post.setTitle(request.getTitle());
        post.setText(request.getText());
        repository.save(post);
        emailService.sendTestMessage(userService.getUserById(userId).getEmail(), request.getTitle(), request.getText());
    }

    public List<Post> getPostsByUserId(Integer id){
        User user = userService.getUserById(id);
        return user.getPosts();
    }
}
