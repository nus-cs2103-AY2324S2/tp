package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Interviewer in Tether.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interviewer extends Person {

    private final Type type = Type.INTERVIEWER;

    /**
     * Every field must be present and not null.
     */
    public Interviewer(Name name, Phone phone, Email email, Remark remark, Set<Tag> tags) {
        super(name, phone, email, remark, tags);
        this.tags.add(new Tag("Interviewer"));
    }

    @Override
    public String getPersonType() {
        return type.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
