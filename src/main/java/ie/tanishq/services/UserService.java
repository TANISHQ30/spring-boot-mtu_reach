package ie.tanishq.services;
import ie.tanishq.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean existsById(String s);
    User addNewUser(String email, String password, String Role, String firstName, String lastName);
    boolean existsByEmail(String email);
    List<User> getAllUsers();

    User save(User user);

    Optional<User> findByEmail(String email);

}
