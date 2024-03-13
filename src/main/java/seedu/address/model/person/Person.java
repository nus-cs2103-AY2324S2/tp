package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;
import seedu.address.model.weeknumber.WeekNumber;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final NusNet nusNet;

    // Data fields
    private final Address address;
    private final Set<WeekNumber> attendance = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, NusNet nusNet,
                  Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, attendance, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nusNet = nusNet;
        this.address = address;
        this.tags.addAll(tags);
    }
    /**
     * Every field must be present and not null. Alternate constructor to instantiate a person with
     * an existing attendance.
     */
    public Person(Name name, Phone phone, Email email, NusNet nusNet,
                  Address address, Set<WeekNumber> attendance, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, attendance, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nusNet = nusNet;
        this.address = address;
        this.attendance.addAll(attendance);
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

    public NusNet getNusNet() {
        return nusNet;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable set of Week Numbers, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<WeekNumber> getAttendance() {
        return Collections.unmodifiableSet(attendance);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same nusnet id.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (otherPerson == null) {
            return false;
        }


        // NUSNET is used as the unique identifier for a person
        return otherPerson.getNusNet().equals(this.getNusNet());
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
                && nusNet.equals(otherPerson.nusNet)
                && address.equals(otherPerson.address)
                && attendance.equals(otherPerson.attendance)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, nusNet, address, attendance, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("nusNet", nusNet)
                .add("address", address)
                .add("attendance", attendance)
                .add("tags", tags)
                .toString();
    }

}