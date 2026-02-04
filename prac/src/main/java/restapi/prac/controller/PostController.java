package restapi.prac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.prac.model.Post;
import restapi.prac.service.PostService;
import jakarta.servlet.http.HttpSession;
import restapi.prac.dto.PostResponseDto;
import restapi.prac.model.User;



import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostResponseDto>> listPost(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<PostResponseDto> posts = postService.getPosts(pageable)
                .map(PostResponseDto::new);

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id){
        Optional<Post> postOpt = postService.getPost(id);

        return postOpt
                .map(post -> ResponseEntity.ok(new PostResponseDto(post)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody Post post, HttpSession session){

        User loginUser = (User) session.getAttribute("loginUser");

        if(loginUser == null){
            return ResponseEntity.status(401).build();
        }

        Post createdPost = postService.createPost(post, loginUser);
        return ResponseEntity.ok(new PostResponseDto(createdPost));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatePost){
        Optional<Post> updated = postService.updatePost(id, updatePost);
        return updated.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        boolean deleted = postService.deletePost(id);
        if(deleted){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
