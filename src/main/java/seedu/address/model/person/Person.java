package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Email email;
    private final Course course;
    private final Role role;

    // Data fields
    private final Optional<Address> address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(
            Name name, Optional<Phone> phone, Email email, Role role,
            Optional<Address> address, Course course, Set<Tag> tags) {

        requireAllNonNull(name, phone, email, role, address, course, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.address = address;
        this.course = course;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Optional<Phone> getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public Course getCourse() {
        return course;
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
                && role.equals(otherPerson.role)
                && address.equals(otherPerson.address)
                && course.equals(otherPerson.course)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, role, address, course, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("role", role)
                .add("address", address)
                .add("course", course)
                .add("tags", tags)
                .toString();
    }

}
