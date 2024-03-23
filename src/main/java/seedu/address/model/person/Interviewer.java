package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.person.enums.Type;
import seedu.address.model.tag.Tag;

/**
 * Represents an Interviewer in Tether.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interviewer extends Person {

    private final Type type = Type.INTERVIEWER;

    private InterviewerStatus status;

    /**
     * Every field must be present and not null.
     */
    public Interviewer(Name name, Phone phone, Email email, Remark remark, InterviewerStatus status, Set<Tag> tags) {
        super(name, phone, email, remark, tags);
        this.tags.add(new Tag("Interviewer"));
        this.status = status;
    }

    @Override
    public String getPersonType() {
        return type.toString();
    }

    @Override
    public String getStatus() {
        return status.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
