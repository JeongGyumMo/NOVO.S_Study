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

    public Comment createComment(Long postId, String content, String writer) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setWriter(writer);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    }

    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
