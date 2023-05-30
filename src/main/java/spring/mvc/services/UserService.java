package spring.mvc.services;

import org.springframework.transaction.annotation.Transactional;
import spring.mvc.models.User;

import java.util.List;

public interface UserService {


    List<User> findAll();

    User findOne(int id);

    @Transactional
    void savePerson(User user);

    @Transactional
    void updatePerson(int id, User updateduser);

    @Transactional
    void deletePerson(int id);
}
