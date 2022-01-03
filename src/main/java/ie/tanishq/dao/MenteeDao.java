package ie.tanishq.dao;

import ie.tanishq.entities.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenteeDao extends JpaRepository<Mentee, Integer> {

    @Override
    boolean existsById(Integer integer);
    boolean existsByEmail(String email);
    Mentee getMenteeByMenteeId(int id);

    @Override
    void deleteById(Integer integer);

    List<Mentee> findAllByOrderByMenteeIdAsc();

}
