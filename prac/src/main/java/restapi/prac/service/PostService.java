package restapi.prac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import restapi.prac.model.Post;
import restapi.prac.repository.PostRepository;
import restapi.prac.model.User;
import restapi.prac.repository.UserRepository;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Page<Post> getPosts(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    public Optional<Post> getPost(Long id){
        return postRepository.findById(id);
    }

    public Post createPost(Post post, User loginUser){
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        post.setUser(user);
        return postRepository.save(post);
    }

    public Optional<Post> updatePost(Long id, Post updatePost){
        return postRepository.findById(id).map(post -> {
            post.setTitle(updatePost.getTitle());
            post.setContent(updatePost.getContent());
            return postRepository.save(post);
        });
    }


    public boolean deletePost(Long id, User loginUser){
        return postRepository.findById(id).map(post -> {

            if (!post.getUser().getId().equals(loginUser.getId())) {
                throw new RuntimeException("삭제 권한 없음");
            }

            postRepository.delete(post);
            return true;

        }).orElse(false);
    }
}
