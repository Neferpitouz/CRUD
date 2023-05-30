package spring.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mvc.models.Person;
import spring.mvc.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PeopleServiceImpl implements PeopleService{

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Override
    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Override
    @Transactional
    public void savePerson (Person person) {
        peopleRepository.save(person);
    }

    @Override
    @Transactional
    public void updatePerson (int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Override
    @Transactional
    public void deletePerson (int id) {
        peopleRepository.deleteById(id);
    }

}
