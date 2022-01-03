package ie.tanishq.services;

import ie.tanishq.dao.NoteDao;
import ie.tanishq.entities.Mentee;
import ie.tanishq.entities.Note;
import ie.tanishq.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteDao noteDao;

    @Override
    public boolean existsById(Integer id) {
        return noteDao.existsById(id);
    }

    /*@Override
    public Note getNoteByMenteeId(int menteeId) {
        return noteDao.getNoteByMenteeId(menteeId);
    }*/

    @Override
    public Note addNewNote(String text, Mentee noteMentee) {
        Note note = new Note(text, noteMentee);
        System.out.println("Note saved successfully");
        return noteDao.save(note);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteDao.findAll();
    }

    @Override
    public List<Note> getNotesByMenteeId(Integer menteeId) {
        return noteDao.findAllByNoteMentee_MenteeId(menteeId);
    }

    @Override
    public Note saveNote(Note note) {
        return noteDao.save(note);
    }

    @Override
    public int getCountOfAllNotes() {
        return getAllNotes().size();
    }
}
