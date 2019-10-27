package pl.sda.fencebook.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fencebook.post.model.NewPostRequest;
import pl.sda.fencebook.post.model.Post;
import pl.sda.fencebook.post.model.RemovePostRequest;
import pl.sda.fencebook.post.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user/post")
public class PostController {
    private PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping
    public void createNewPost(@RequestBody NewPostRequest request){
        service.generateNewPost(request);
    }

    @PostMapping(value = "/remove")
    public void removePost(@RequestBody RemovePostRequest request){
        service.removePost(request);
    }

    @GetMapping
    public List<Post> getUserPosts(@RequestParam Integer id){
        return service.getPostsByUserId(id);
    }

    @PostMapping(value = "/react")
    public void addReaction(@RequestParam Integer postId, @RequestParam Integer userId, @RequestParam Integer reactionId){
        service.addReactionToPost(postId, reactionId, userId);
    }
}
