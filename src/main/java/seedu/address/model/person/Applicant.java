package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.person.enums.Type;
import seedu.address.model.tag.Tag;

/**
 * Represents an Applicant in the Tether.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant extends Person {

    private final Type type = Type.APPLICANT;

    private ApplicantStatus status;

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Remark remark, ApplicantStatus status, Set<Tag> tags) {
        super(name, phone, email, remark, tags);
        this.tags.add(new Tag("Applicant"));
        this.status = status;
    }

    @Override
    public boolean isSamePerson(Person otherPerson) {
        return super.isSamePerson(otherPerson);
    }

    @Override
    public String getPersonType() {
        return type.toString();
    }

    @Override
    public String getStatus() {
        return status.toString().toLowerCase();
    }

    @Override
    public String toString() {
        return super.toString() + "\nStatus: " + status.toString();
    }
}
