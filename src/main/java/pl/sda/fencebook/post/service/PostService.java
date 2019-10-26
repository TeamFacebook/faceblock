package pl.sda.fencebook.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fencebook.post.model.NewPostRequest;
import pl.sda.fencebook.post.model.Post;
import pl.sda.fencebook.post.model.PostRepository;
import pl.sda.fencebook.user.model.User;
import pl.sda.fencebook.user.service.UserService;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    private UserService userService;
    private PostRepository repository;

    @Autowired
    public PostService(UserService userService, PostRepository repository) {
        this.userService = userService;
        this.repository = repository;
    }

    public void generateNewPost(Integer userId, NewPostRequest request){
        Post post = new Post();
        post.setAuthorId(userId);
        post.setDate(new Date());
        post.setTitle(request.getTitle());
        post.setText(request.getText());
        repository.save(post);
    }

    public List<Post> getPostsByUserId(Integer id){
        User user = userService.getUserById(id);
        return user.getPosts();
    }
}
