package spring.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.mvc.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
