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
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword){

        Pageable pageable = PageRequest.of(page, size);

        Page<Post> posts;

        if(keyword == null || keyword.trim().isEmpty()){
            posts = postService.getPosts(pageable);
        } else {
            posts = postService.searchPosts(keyword, pageable);
        }

        Page<PostResponseDto> response = posts.map(PostResponseDto::new);
        return ResponseEntity.ok(response);
    }

    // 게시글 상세
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id){
        Optional<Post> postOpt = postService.getPost(id);

        return postOpt
                .map(post -> ResponseEntity.ok(new PostResponseDto(post)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 게시글 작성
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody Post post, HttpSession session){

        User loginUser = (User) session.getAttribute("loginUser");

        if(loginUser == null){
            return ResponseEntity.status(401).build();
        }

        Post createdPost = postService.createPost(post, loginUser);
        return ResponseEntity.ok(new PostResponseDto(createdPost));
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long id,
            @RequestBody Post updatePost) {

        Optional<Post> updated = postService.updatePost(id, updatePost);

        if (updated.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, HttpSession session){

        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser == null){
            return ResponseEntity.status(401).build();
        }

        boolean deleted = postService.deletePost(id, loginUser);

        if(deleted){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}