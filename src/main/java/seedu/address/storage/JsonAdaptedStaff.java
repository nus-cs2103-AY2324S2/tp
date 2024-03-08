package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedStaff extends JsonAdaptedPerson{

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String salary;
    private final String employment;


    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedStaff(Person source) {
        super(source);
        Staff staff = (Staff) source;
        salary = staff.getSalary().value;
        employment = staff.getEmployment().employment;
    }
}
