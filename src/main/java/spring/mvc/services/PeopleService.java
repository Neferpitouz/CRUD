package spring.mvc.services;

import org.springframework.transaction.annotation.Transactional;
import spring.mvc.models.Person;

import java.util.List;

public interface PeopleService {


    List<Person> findAll();

    Person findOne(int id);

    @Transactional
    void savePerson(Person person);

    @Transactional
    void updatePerson(int id, Person updatedPerson);

    @Transactional
    void deletePerson(int id);
}
