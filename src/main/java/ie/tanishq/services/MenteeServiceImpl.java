package ie.tanishq.services;

import ie.tanishq.dao.MenteeDao;
import ie.tanishq.dao.NoteDao;
import ie.tanishq.entities.Mentee;
import ie.tanishq.entities.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MenteeServiceImpl implements MenteeService {

    @Autowired
    MenteeDao menteeDao;

    @Autowired
    NoteDao noteDao;

    @Override
    public boolean existsById(Integer menteeId) {
        return menteeDao.existsById(menteeId);
    }

    @Override
    public Mentee addNewMentee(String email, String firstName, String lastName) {
        if(menteeDao.existsByEmail(email)) {
            log.debug("Mentee/Student exists in the database");
            return null;
        }
        else    {
            Mentee mentee = new Mentee(email, firstName, lastName);
            log.debug(firstName + " - mentee added successfully!");
            return menteeDao.save(mentee);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return menteeDao.existsByEmail(email);
    }

    @Override
    public List<Mentee> getAllMentees() {
        return menteeDao.findAll();
    }

    @Override
    public Mentee getMenteeByMenteeId(Integer menteeId) {
        return menteeDao.getMenteeByMenteeId(menteeId);
    }

    @Override
    public boolean delete(Integer menteeId) {
        if(menteeDao.existsById(menteeId)) {
            menteeDao.deleteById(menteeId);
            return true;
        }
        else    {
            return false;
        }
    }

    @Override
    public List<Mentee> findAllMenteesInAsc() {
        return menteeDao.findAllByOrderByMenteeIdAsc();
    }

    @Override
    public Mentee saveMentee(Mentee mentee) {
        if(menteeDao.existsByEmail(mentee.getEmail())) {
            log.debug("Mentee/Student exists in the database");
            return null;
        }
        else    {
            return menteeDao.save(mentee);
        }
    }

    @Override
    public List<Mentee> findAllInactiveStudents() {
        List<Mentee> inactiveMentees = new ArrayList<>();
        List<Mentee> allMentees = findAllMenteesInAsc();
        for(Mentee mentee: allMentees){
            List<Note> allNotesForAMentee = noteDao.findAllByNoteMentee_MenteeId(mentee.getMenteeId());
            //allNotesForAMentee.forEach(System.out::println);
            if ((allNotesForAMentee.size()) == 0){
                inactiveMentees.add(mentee);
            }
        }
        return inactiveMentees;
    }

    @Override
    public List<Mentee> findAllActiveStudents() {
        List<Mentee> activeMentees = new ArrayList<>();
        List<Mentee> allMentees = findAllMenteesInAsc();
        for(Mentee mentee: allMentees){
            List<Note> allNotesForAMentee = noteDao.findAllByNoteMentee_MenteeId(mentee.getMenteeId());
            //allNotesForAMentee.forEach(System.out::println);
            if ((allNotesForAMentee.size()) >= 4){
                activeMentees.add(mentee);
            }
        }
        return activeMentees;
    }

    @Override
    public int countAllMentees() {
        List<Mentee> listALlMentees = menteeDao.findAllByOrderByMenteeIdAsc();
        return listALlMentees.size();
    }

    @Override
    public int countAllActiveMentees() {
        return findAllActiveStudents().size();
    }

    @Override
    public int countAllInactiveMentees() {
        return findAllInactiveStudents().size();
    }
}