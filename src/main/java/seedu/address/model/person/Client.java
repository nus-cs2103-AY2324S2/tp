package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client extends Person {

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Client)) {
            return false;
        }

        Client otherPerson = (Client) other;
        return this.getName().equals(otherPerson.getName())
                && this.getPhone().equals(otherPerson.getPhone())
                && this.getEmail().equals(otherPerson.getEmail())
                && this.getAddress().equals(otherPerson.getAddress())
                && this.getTags().equals(otherPerson.getTags());
    }
}
