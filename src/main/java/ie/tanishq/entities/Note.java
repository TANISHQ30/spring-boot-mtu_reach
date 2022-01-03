package ie.tanishq.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ie.tanishq.entities.Mentee;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime created = LocalDateTime.now();

    @Column(nullable = false)
    private String text;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="student_id", updatable=false)
    private Mentee noteMentee;

    public Note(String text, Mentee noteMentee){
        this.text = text;
        this.noteMentee = noteMentee;
    }

}
