package ie.tanishq.forms;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
public class NewNoteForm {
    @Size(min=5, max=50)
    private String text;

    @NonNull
    private int menteeId;

    public NewNoteForm() {

    }
}
