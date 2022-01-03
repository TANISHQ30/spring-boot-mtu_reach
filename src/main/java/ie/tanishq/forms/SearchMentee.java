package ie.tanishq.forms;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
public class SearchMentee {

    @Size(min=3, max=50)
    private int menteeId;

}
