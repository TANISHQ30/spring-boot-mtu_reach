package ie.tanishq.dao;

import ie.tanishq.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {

    @Override
    boolean existsById(String s);
    boolean existsByEmail(String email);

}
