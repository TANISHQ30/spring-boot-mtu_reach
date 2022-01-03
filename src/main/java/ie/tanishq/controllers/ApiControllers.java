package ie.tanishq.controllers;

import ie.tanishq.entities.Mentee;
import ie.tanishq.entities.Note;
import ie.tanishq.services.MenteeService;
import ie.tanishq.services.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("api")
public class ApiControllers {

    @Autowired
    MenteeService menteeService;

    @Autowired
    NoteService noteService;

    //Show all mentee's
    @GetMapping("/mentees")
    List<Mentee> showAllMentees() {
        return menteeService.getAllMentees();
    }

    //Show all notes for all mentee's
    @GetMapping("/notes")
    List<Note> showAllNotes() {
        return noteService.getAllNotes();
    }

    //Return all the notes for a mentee
    @GetMapping("/mentee/notes/{menteeId}")
    public List<Note> getMenteeNotesByMenteeId(@PathVariable String menteeId) {
        return noteService.getNotesByMenteeId(Integer.parseInt(menteeId));
    }

    //Delete a mentee
    @GetMapping("/mentee/delete/{menteeId}")
    public String deleteAMentee(@PathVariable String menteeId) {
        Mentee mentee = menteeService.getMenteeByMenteeId(Integer.parseInt(menteeId));
        if (mentee == null){
            log.debug("Mentee with id = " + menteeId + " not found");
        }
        menteeService.delete(Integer.parseInt(menteeId));
        return "Mentee with id = " + menteeId + " deleted successfully";
    }
}
