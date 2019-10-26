package pl.sda.fencebook.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fencebook.post.model.NewPostRequest;
import pl.sda.fencebook.post.model.Post;
import pl.sda.fencebook.post.service.PostService;

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
    public void createNewPost(@RequestParam Integer id, @RequestBody NewPostRequest request){
        service.generateNewPost(id, request);
    }

    @GetMapping
    public List<Post> getUserPosts(@RequestParam Integer id){
        return service.getPostsByUserId(id);
    }
}
