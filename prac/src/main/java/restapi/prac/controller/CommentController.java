package restapi.prac.controller;

import org.springframework.web.bind.annotation.*;
import restapi.prac.model.Comment;
import restapi.prac.service.CommentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 작성
    @PostMapping
    public Comment createComment(@RequestParam Long postId,
                                 @RequestBody Map<String, String> body) {
        return commentService.createComment(postId, body.get("content"));
    }

    // 특정 게시글의 댓글 목록 조회
    @GetMapping("/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
