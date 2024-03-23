package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a sponsor in the hackathon.
 */
public class Sponsor extends Person {
    /**
     * Constructs a sponsor. Every field must be present and not null.
     */
    public Sponsor(Name name, Phone phone, Email email, Address address, Category category, Set<Tag> tags) {
        super(name, phone, email, address, category, tags);
    }

    @Override
    public boolean isSamePerson(Person other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Sponsor)) {
            return false;
        }
        return super.isSamePerson(other);
    }
}
