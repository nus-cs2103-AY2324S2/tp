package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Represents a Housekeeper in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Housekeeper extends Person {
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Housekeeper(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Type type) {
        super(name, phone, email, address, tags, type);
    }

    /**
     * Returns true if both housekeepers have the same identity and data fields.
     * This defines a stronger notion of equality between two housekeepers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Housekeeper)) {
            return false;
        }

        Housekeeper otherPerson = (Housekeeper) other;
        return this.getName().equals(otherPerson.getName())
                && this.getPhone().equals(otherPerson.getPhone())
                && this.getEmail().equals(otherPerson.getEmail())
                && this.getAddress().equals(otherPerson.getAddress())
                && this.getTags().equals(otherPerson.getTags())
                && this.getType().equals(otherPerson.getType());
    }

    @Override
    public boolean isClient() {
        return false;
    }
}
