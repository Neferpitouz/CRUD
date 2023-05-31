package spring.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mvc.dao.UserDAO;
import spring.mvc.models.User;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.getAll();
    }

    @Override
    public User findOne(int id) {
        Optional<User> foundPerson = Optional.ofNullable(userDAO.getById(id));
        return foundPerson.orElse(null);
    }

    @Override
    @Transactional
    public void savePerson (User user) {
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void updatePerson (int id, User updatedUser) {
        User existingUser = userDAO.getById(id);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setAge(updatedUser.getAge());
            existingUser.setEmail(updatedUser.getEmail());
            userDAO.update(existingUser);
        }
    }

    @Override
    @Transactional
    public void deletePerson (int id) {
        userDAO.delete(id);
    }

}
