package seedu.address.model.application;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents an Applicant.
 */
public class Applicant extends Person {

    private Stage currentStage;

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Note note, String noteDate) {
        super(name, phone, email, address, tags, note, noteDate);

        // Default starting stage.
        currentStage = Application.INITIAL_APPLICATION;
    }

    // TODO: Add Applicant logic here.
}
