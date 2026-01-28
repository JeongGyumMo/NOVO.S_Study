package restapi.prac.service;

import org.springframework.stereotype.Service;
import restapi.prac.model.Comment;
import restapi.prac.model.Post;
import restapi.prac.repository.CommentRepository;
import restapi.prac.repository.PostRepository;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    // 댓글 작성
    public Comment createComment(Long postId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    // 댓글 목록 조회
    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
