package scrolls.elder.model.person;

import java.util.Optional;
import java.util.Set;

import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.model.tag.Tag;

/**
 * Represents a Volunteer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Volunteer extends Person {
    public Volunteer(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                     Optional<Name> pairedWithName, Optional<Integer> pairedWithId) {
        super(name, phone, email, address, tags, new Role("volunteer"), pairedWithName, pairedWithId);
    }

    /**
     * Creates a volunteer with the data of {@code p} and corresponding ID.
     */
    public Volunteer(int id, Person p) {
        super(id, p);
    }

    @Override
    public boolean isVolunteer() {
        return true;
    }

    @Override
    public boolean isBefriendee() {
        return false;
    }

    @Override
    public Role getRole() {
        return this.role;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Volunteer)) {
            return false;
        }

        Volunteer otherVolunteer = (Volunteer) other;
        return name.equals(otherVolunteer.name)
                && phone.equals(otherVolunteer.phone)
                && email.equals(otherVolunteer.email)
                && address.equals(otherVolunteer.address)
                && tags.equals(otherVolunteer.tags)
                && pairedWithName.equals(otherVolunteer.pairedWithName)
                && pairedWithId.equals(otherVolunteer.pairedWithId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("role", role)
                .add("pairedWithName", pairedWithName.orElse(Name.getNone()))
                .add("pairedWithId", pairedWithId.orElse(-1))
                .toString();
    }
}
