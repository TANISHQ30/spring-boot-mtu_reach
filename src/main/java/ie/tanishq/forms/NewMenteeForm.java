package ie.tanishq.forms;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
public class NewMenteeForm {
    @Size(min=3, max=30)
    private String firstName;

    @Size(min=3, max=30)
    private String lastName;

    @Size(min=3, max=30)
    private String email;

}
