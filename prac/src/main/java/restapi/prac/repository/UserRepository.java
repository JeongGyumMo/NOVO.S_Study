package restapi.prac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi.prac.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
