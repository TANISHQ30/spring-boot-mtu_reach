package ie.tanishq;

import ie.tanishq.dao.UserDao;
import ie.tanishq.entities.Mentee;
import ie.tanishq.entities.Note;
import ie.tanishq.entities.User;
import ie.tanishq.services.MenteeService;
import ie.tanishq.services.NoteService;
import ie.tanishq.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    UserService userService;

    @Autowired
    MenteeService menteeService;

    @Autowired
    NoteService noteService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        User mentor = new User("mentor@mtu.ie", passwordEncoder.encode("password"), "REACH_MENTOR","John", "Murphy" );
        User reviewer = new User("review@mtu.ie",  passwordEncoder.encode("password"), "REACH_REVIEWER", "David", "Warner");
        User api = new User("api@mtu.ie",  passwordEncoder.encode("password"), "REACH_API", "Jean","Fitzpat");
        User admin = new User("admin@mtu.ie", passwordEncoder.encode("password"), "REACH_ADMIN","Tanishq", "Deshmukh" );
        userService.save(mentor);
        userService.save(reviewer);
        userService.save(api);
        userService.save(admin);

        Mentee mentee1 = new Mentee(1, "cloving0@feedburner.com", "Cammy", "Loving");
        Mentee mentee2 = new Mentee(2, "mwoodburne1@51.la", "Morgen", "Woodburne");
        Mentee mentee3 = new Mentee(3, "bhaddrill2@i2i.jp", "Benny", "Haddrill");
        menteeService.saveMentee(mentee1);
        menteeService.saveMentee(mentee2);
        menteeService.saveMentee(mentee3);

        Note note1 = new Note(1, LocalDateTime.now(), "Note for mentee1 - 1", mentee1);
        Note note2 = new Note(2, LocalDateTime.now(), "Note for mentee2", mentee2);
        Note note3 = new Note(3, LocalDateTime.now(), "Note for mentee3", mentee3);
        Note note4 = new Note(4, LocalDateTime.now(), "Note for mentee1 - 2", mentee1);
        Note note5 = new Note(5, LocalDateTime.now(), "Note for mentee1 - 3", mentee1);
        Note note6 = new Note(6, LocalDateTime.now(), "Note for mentee1 - 4", mentee1);
        noteService.saveNote(note1);
        noteService.saveNote(note2);
        noteService.saveNote(note3);
        noteService.saveNote(note4);
        noteService.saveNote(note5);
        noteService.saveNote(note6);


    }

}
