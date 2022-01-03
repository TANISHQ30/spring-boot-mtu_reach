package ie.tanishq.services;

import ie.tanishq.dao.UserDao;
import ie.tanishq.entities.Mentee;
import ie.tanishq.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public boolean existsById(String email) {
        return userDao.existsById(email);
    }

    @Override
    public User addNewUser(String email, String password, String role, String firstName, String lastName) {
        if(userDao.existsByEmail(email)) {
            System.out.println("User exists in the database");
            return null;
        }
        else    {
            User user = new User(email, password, role, firstName, lastName);
            System.out.println(firstName + " - user added successfully!");
            return userDao.save(user);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {
        if(existsByEmail(user.getEmail())) {
            log.debug("User already exists in the database");
            return null;
        }
        return userDao.save(user);
    }

    @Override
    public Optional<User> findByEmail(String username) {
        return userDao.findById(username);
    }
}
