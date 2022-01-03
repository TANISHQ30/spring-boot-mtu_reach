package ie.tanishq.controllers;

import ie.tanishq.entities.Mentee;
import ie.tanishq.entities.Note;
import ie.tanishq.forms.NewNoteForm;
import ie.tanishq.services.MenteeService;
import ie.tanishq.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    MenteeService menteeService;

    @GetMapping("/note/{menteeId}")
    public String showNotesByMenteeId(@PathVariable("menteeId") int menteeid, Model model)   {
        List<Note> notes = noteService.getNotesByMenteeId(menteeid);
        if(notes == null)  {
            model.addAttribute("menteeId", menteeid);
            return "notfound";
        }
        model.addAttribute("mentee", menteeService.getMenteeByMenteeId(menteeid));
        model.addAttribute("notes", notes);
        return "note/note";
    }

    @GetMapping("/note/showall")
    public String showAllNotes(Model model)   {
        model.addAttribute("notes", noteService.getAllNotes());
        return "note/showall";
    }

    @GetMapping("/note/{menteeId}/add")
    public String showForm(@PathVariable("menteeId") int menteeid, Model model)   {
        //System.out.println(menteeService.findAllMenteesInAsc());
        model.addAttribute("newNoteForm", new NewNoteForm());
        model.addAttribute("mentee", menteeService.getMenteeByMenteeId(menteeid));
        return "note/add";
    }

    @PostMapping("/note/{menteeId}/add/save")
    public String addMenteePost(@PathVariable("menteeId") int menteeid, @Valid @ModelAttribute("newNoteForm") NewNoteForm newNoteForm, BindingResult bindingResult, Model model)    {
       // System.out.println(menteeService.findAllMenteesInAsc());
        if(bindingResult.hasErrors()) {
            model.addAttribute("mentee", menteeService.getMenteeByMenteeId(menteeid));
            //return "note/" + menteeid + "/add";
            return "/note/{menteeId}/add";
        }
        Mentee mentee = menteeService.getMenteeByMenteeId(menteeid);
        Note note = noteService.addNewNote(newNoteForm.getText(), mentee);
        return "redirect:/note/"+mentee.getMenteeId();
    }

}
