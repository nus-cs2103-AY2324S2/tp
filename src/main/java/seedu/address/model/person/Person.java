package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
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
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Meeting meeting;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Policy> policies = new HashSet<>();

    /**
     * Every field must be present and not null.
     * @param name The name of the person.
     * @param phone The phone number of the person.
     * @param email The email address of the person.
     * @param address The address of the person.
     * @param meeting The meeting details of the person.
     * @param tags The set of tags associated with the person.
     * @param policies The set of policies associated with the person.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Meeting meeting, Set<Tag> tags, Set<Policy> policies) {
        requireAllNonNull(name, phone, email, address, meeting, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.meeting = meeting;
        this.tags.addAll(tags);
        this.policies.addAll(policies); // Initialize with provided policies
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

    public Address getAddress() {
        return address;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public Set<Policy> getPolicies() {
        return Collections.unmodifiableSet(policies);
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
                && address.equals(otherPerson.address)
                && meeting.equals(otherPerson.meeting)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, meeting, tags);
    }

    /**
     * Returns a string representation of the person.
     * The string representation contains the person's name, phone, email, address, tags, and policies.
     * Example:
     *     Alice Pauline Phone: 12345678 Email: alice@example.com Address: 123, Jurong West Ave 6,
     *     #08-111 Tags: friends family Policies: [SuperSaver] [HealthGuard]
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("meeting", meeting)
                .add("tags", tags)
                .add("policies", policies)
                .toString();
    }
}
