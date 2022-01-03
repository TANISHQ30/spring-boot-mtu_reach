package ie.tanishq.dao;

import ie.tanishq.entities.Mentee;
import ie.tanishq.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteDao extends JpaRepository<Note, Integer> {

    @Override
    boolean existsById(Integer id);
    List<Note> findAllByNoteMentee_MenteeId(int menteeId);

}
