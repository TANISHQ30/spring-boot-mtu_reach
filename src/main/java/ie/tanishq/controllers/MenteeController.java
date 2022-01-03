package ie.tanishq.controllers;

import ie.tanishq.entities.Mentee;
import ie.tanishq.forms.NewMenteeForm;
import ie.tanishq.forms.NewNoteForm;
import ie.tanishq.forms.SearchMentee;
import ie.tanishq.services.MenteeService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class MenteeController {

    @Autowired
    MenteeService menteeService;

    @GetMapping("/mentee/{menteeId}")
    public String showMenteeByStudentId(@PathVariable("menteeId") int menteeid, Model model)   {
        Mentee mentee = menteeService.getMenteeByMenteeId(menteeid);
        if(mentee == null)  {
            model.addAttribute("menteeId", menteeid);
            return "notfound";
        }
        model.addAttribute("mentee", mentee);
        return "mentee/mentee";
    }

    @GetMapping("/mentee/showall")
    public String showAllMentees(Model model)   {
        model.addAttribute("mentees", menteeService.getAllMentees());
       // menteeService.getAllMentees().forEach(System.out::println);
        return "mentee/showall";
    }

    @GetMapping("/mentee/add")
    public String showForm(@ModelAttribute("newMenteeForm") NewMenteeForm newMenteeForm, Model model)   {
        return "mentee/add";
    }

    @PostMapping("/mentee/add/save")
    public String addMenteePost(@Valid @ModelAttribute("newMenteeForm") NewMenteeForm newMenteeForm, BindingResult bindingResult)    {
        if(bindingResult.hasErrors())
            return "mentee/add";
        Mentee mentee = menteeService.addNewMentee(newMenteeForm.getEmail(), newMenteeForm.getFirstName(), newMenteeForm.getLastName());
        return "redirect:/mentee/showall";
    }

    /*@GetMapping("/mentee/search")
    public String searchMenteeGet(Model model, @ModelAttribute("newMenteeForm") NewMenteeForm newMenteeForm)   {
        model.addAttribute("newMenteeForm", new NewMenteeForm());
        return "mentee/showall";
    }*/

    /*@PostMapping("/mentee/search")
    public String searchMenteePost(@Valid @ModelAttribute("newMenteeForm") NewMenteeForm newMenteeForm, BindingResult bindingResult, Model model)   {
        if(bindingResult.hasErrors()) {
            model.addAttribute("mentees", menteeService.getAllMentees());
            return "mentee/showall";
        }
        System.out.println(newMenteeForm.getMenteeId());
        return "mentee/showall";
        /*if(menteeService.existsById(newMenteeForm.getMenteeId()))  {
            model.addAttribute("mentees", menteeService.getMenteeByMenteeId(newMenteeForm.getMenteeId()));
            return "mentee/showall";
        }
        log.debug("Mentee with id = ", newMenteeForm.getMenteeId(), " not present in the database");
        model.addAttribute("menteeId", newMenteeForm.getMenteeId());
        return "notfound";
    }*/

    @GetMapping("/mentee/search")
    public String findMentee(Model model, String keyword)    {
        int id = Integer.parseInt(keyword);
        if(menteeService.existsById(id))  {
            model.addAttribute("mentees", menteeService.getMenteeByMenteeId(id));
            return "mentee/showall";
        }
        log.debug("Mentee with id = ", id, " not present in the database");
        model.addAttribute("menteeId", id);
        return "notfound";
    }

    @GetMapping("/mentee/delete/{id}")
    public String deleteAMentee(@PathVariable("id") int menteeId) {
        if(!menteeService.existsById(menteeId)) {
            log.debug("Mentee with id = ", menteeId, " not present in the database");
            return "redirect:/mentee/showall";
        }
        menteeService.delete(menteeId);
        return "redirect:/mentee/showall";
    }

    @GetMapping("/mentee/inactive")
    public String showInactiveMentees(Model model)  {
        model.addAttribute("mentees", menteeService.findAllInactiveStudents());
        //menteeService.findAllInactiveStudents().forEach(System.out::println);
        return "mentee/showall";
    }

    @GetMapping("/mentee/active")
    public String showActiveMentees(Model model)  {
        model.addAttribute("mentees", menteeService.findAllActiveStudents());
        //menteeService.findAllActiveStudents().forEach(System.out::println);
        return "mentee/showall";
    }
}
