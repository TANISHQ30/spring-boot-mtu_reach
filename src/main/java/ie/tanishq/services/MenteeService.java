package ie.tanishq.services;

import ie.tanishq.entities.Mentee;

import java.util.List;

public interface MenteeService {

    boolean existsById(Integer integer);
    Mentee addNewMentee(String email, String firstName, String lastName);
    boolean existsByEmail(String email);
    /*public List<Mentee> getAllTownsInACounty(County county) {
        return townDao.findAllByTownCounty(county);
    }*/

    List<Mentee> getAllMentees();

    Mentee getMenteeByMenteeId(Integer menteeId);

    boolean delete(Integer integer);

    List<Mentee> findAllMenteesInAsc();

    Mentee saveMentee(Mentee mentee);

    List<Mentee> findAllInactiveStudents();

    List<Mentee> findAllActiveStudents();

    int countAllMentees();

    int countAllActiveMentees();
    int countAllInactiveMentees();


}
