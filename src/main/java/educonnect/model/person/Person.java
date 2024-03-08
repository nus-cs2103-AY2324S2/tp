package educonnect.model.person;

import static educonnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import educonnect.commons.util.ToStringBuilder;
import educonnect.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final StudentId studentId;
    private final TelegramHandle telegramHandle;
    //private final Phone phone;
    private final Email email;

    // Data fields
    //private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, StudentId studentId, Email email, TelegramHandle telegramHandle, Set<Tag> tags) {
        requireAllNonNull(name, studentId, email, telegramHandle, tags);
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Email getEmail() {
        return email;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
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
                && otherPerson.getName().equals(getName())
                && otherPerson.getStudentId().equals(getStudentId());
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
                && studentId.equals(otherPerson.studentId)
                && email.equals(otherPerson.email)
                && telegramHandle.equals(otherPerson.telegramHandle)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, studentId, email, telegramHandle, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("student id", studentId)
                .add("email", email)
                .add("telegram handle", telegramHandle)
                .add("tags", tags)
                .toString();
    }

}
