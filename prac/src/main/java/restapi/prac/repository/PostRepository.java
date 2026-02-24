package restapi.prac.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restapi.prac.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 제목 또는 내용 검색
    Page<Post> findByTitleContainingOrContentContaining(
            String title,
            String content,
            Pageable pageable
    );
}