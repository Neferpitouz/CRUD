package spring.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.mvc.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
