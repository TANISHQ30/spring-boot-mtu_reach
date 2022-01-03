package ie.tanishq.services;

import ie.tanishq.entities.Mentee;
import ie.tanishq.entities.Note;
import ie.tanishq.entities.User;

import java.util.List;

public interface NoteService {

    boolean existsById(Integer id);
    //Note getNoteByMenteeId(int menteeId);
    Note addNewNote(String text, Mentee noteMentee);

    List<Note> getAllNotes();

    List<Note> getNotesByMenteeId(Integer menteeId);

    Note saveNote(Note note);

    int getCountOfAllNotes();

    //List<Note> findAllByNoteMentee_MenteeId(int menteeId);

}
