package staffconnect.model.person;

import static staffconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.model.meeting.Meeting;
import staffconnect.model.tag.Tag;

/**
 * Represents a Person in the staff book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Venue venue;
    private final Module module;
    private final Set<Tag> tags = new HashSet<>();

    private final Set<Meeting> meetings = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Venue venue, Module module, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, venue, module, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.venue = venue;
        this.module = module;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Venue getVenue() {
        return venue;
    }

    public Module getModule() {
        return module;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if the meeting to add is already tagged to the current person.
     *
     */
    public boolean hasDuplicateMeeting(Meeting toAdd) {
        return meetings.contains(toAdd);
    }

    public boolean setMeeting(Meeting toAdd) {
        return meetings.add(toAdd);
    }


    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && venue.equals(otherPerson.venue)
                && module.equals(otherPerson.module)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, venue, module, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("venue", venue)
                .add("module", module)
                .add("tags", tags)
                .toString();
    }

}
