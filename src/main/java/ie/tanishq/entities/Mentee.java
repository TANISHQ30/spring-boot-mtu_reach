package ie.tanishq.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mentee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menteeId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String surname;

    @OneToMany(mappedBy = "noteMentee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    List<Note> noteForMentee = new ArrayList<>();

    public Mentee(String email, String firstName, String surname) {
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
    }

    public Mentee(int menteeId, String email, String firstName, String surname) {

        this.menteeId = menteeId;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;

    }

    @Override
    public String toString() {
        return "Mentee{" +
                "studentId=" + menteeId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
