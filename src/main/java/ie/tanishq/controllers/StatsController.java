package ie.tanishq.controllers;

import ie.tanishq.services.MenteeService;
import ie.tanishq.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StatsController {

    @Autowired
    MenteeService menteeService;

    @Autowired
    NoteService noteService;

    @GetMapping("/stats")
    public String stats(Model model){
        float averageNotesPerMentee;
        if(noteService.getCountOfAllNotes() != 0 && menteeService.countAllMentees() != 0)
            averageNotesPerMentee = (float) (noteService.getCountOfAllNotes() / menteeService.countAllMentees());
        else
            averageNotesPerMentee = 0;
        model.addAttribute("allMentees", menteeService.countAllMentees());
        model.addAttribute("allActiveMentees", menteeService.countAllActiveMentees());
        model.addAttribute("allInactiveMentees", menteeService.countAllInactiveMentees());
        model.addAttribute("allNotes", noteService.getCountOfAllNotes());
        model.addAttribute("avgNotesPerMentee", averageNotesPerMentee);
        return "stats/stats";
    }

}
