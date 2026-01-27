package restapi.prac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restapi.prac.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
