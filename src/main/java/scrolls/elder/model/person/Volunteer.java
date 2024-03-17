package scrolls.elder.model.person;
import java.util.Set;

import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.model.tag.Tag;

/**
 * Represents a Volunteer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Volunteer extends Person {
    public Volunteer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags, new Role("volunteer"));
    }

    @Override
    public boolean isVolunteer() {
        return true;
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
                && pairedWith.equals(otherVolunteer.pairedWith);
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
                .add("pairedWith", pairedWith == null ? "None" : pairedWith.getName())
                .toString();
    }
}
