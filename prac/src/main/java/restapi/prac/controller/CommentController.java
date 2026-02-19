package restapi.prac.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.prac.model.Comment;
import restapi.prac.model.User;
import restapi.prac.service.CommentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestParam Long postId,
                                           @RequestBody Map<String, String> body,
                                           HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }

        User loginUser = (User) session.getAttribute("loginUser");

        Comment comment = commentService.createComment(
                postId,
                body.get("content"),
                loginUser.getUsername()
        );

        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId,
                                           HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }

        User loginUser = (User) session.getAttribute("loginUser");

        Comment comment = commentService.findById(commentId);

        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글 없음");
        }

        if (!comment.getWriter().equals(loginUser.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한 없음");
        }

        commentService.deleteComment(commentId);

        return ResponseEntity.ok("삭제 완료");
    }
}
