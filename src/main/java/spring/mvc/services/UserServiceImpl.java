package spring.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mvc.models.User;
import spring.mvc.repositories.UserRepository;


import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository peopleRepository) {
        this.userRepository = peopleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(int id) {
        Optional<User> foundPerson = userRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Override
    @Transactional
    public void savePerson (User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePerson (int id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public void deletePerson (int id) {
        userRepository.deleteById(id);
    }

}
