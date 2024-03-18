package seedu.address.model.person;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book. Inherits from Person.
 * Guarantees: field values are validated, immutable.
 */
public class Client extends Person {

    // Additional data fields
    private final Optional<Meeting> meeting;

    /**
     * Name must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Meeting meeting, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.meeting = Optional.ofNullable(meeting);
    }

    public Optional<Meeting> getMeeting() {
        return meeting;
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * This defines a stronger notion of equality between two Clients.
     * Returns true if both Clients have the same identity and data fields.
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

        Client otherClient = (Client) other;
        return super.equals(otherClient)
                && meeting.equals(otherClient.meeting);
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), getTags(),
                meeting);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("phone", getPhone())
                .add("email", getEmail())
                .add("address", getAddress())
                .add("meeting", getMeeting())
                .add("tags", getTags())
                .toString();
    }

}
