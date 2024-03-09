package seedu.address.storage;

import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedStaff extends JsonAdaptedPerson {

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
