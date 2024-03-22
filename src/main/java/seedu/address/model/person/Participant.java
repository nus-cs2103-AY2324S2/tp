package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a participants in hackathon.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Participant extends Person {
    private Group group;
    /**
     * Constructs participants. Every field must be present and not null.
     */
    public Participant(Name name, Phone phone, Email email, Address address, Category category, Set<Tag> tags) {
        super(name, phone, email, address, category, tags);
        this.group = new Group();
    }

    @Override
    public boolean isSamePerson(Person other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Participant)) {
            return false;
        }

        Participant otherPerson = (Participant) other;
        return super.isSamePerson(otherPerson);
    }
}
