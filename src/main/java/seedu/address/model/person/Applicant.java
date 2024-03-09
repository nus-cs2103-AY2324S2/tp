package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Applicant in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant extends Person {

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Remark remark, Set<Tag> tags) {
        super(name, phone, email, remark, tags);
    }


    @Override
    public String toString() {
        return "Applicant: " + super.toString();
    }

}
