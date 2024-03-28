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
    private final StudentId studentId;
    private final Name name;
    private final Phone parentPhoneOne;
    private final Phone parentPhoneTwo;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final FormClass formClass;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone parentPhoneOne, Phone parentPhoneTwo, Email email, Address address,
                  StudentId studentId, Set<Tag> tags, FormClass formClass) {
        requireAllNonNull(name, parentPhoneOne, parentPhoneTwo, email, address, tags, studentId);
        this.studentId = studentId;
        this.name = name;
        this.parentPhoneOne = parentPhoneOne;
        this.parentPhoneTwo = parentPhoneTwo;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.formClass = formClass;
    }

    public StudentId getStudentId() {
        return studentId;
    }
    public Name getName() {
        return name;
    }
    public FormClass getFormClass() {
        return formClass;
    }

    public Phone getParentPhoneOne() {
        return parentPhoneOne;
    }

    public Phone getParentPhoneTwo() {
        return parentPhoneTwo;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
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
                && parentPhoneOne.equals(otherPerson.parentPhoneOne)
                && parentPhoneTwo.equals(otherPerson.parentPhoneTwo)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && studentId.equals(otherPerson.studentId);

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, parentPhoneOne, parentPhoneTwo, email, address, studentId, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("parent phone 1", parentPhoneOne)
                .add("parent phone 2", parentPhoneTwo)
                .add("email", email)
                .add("address", address)
                .add("student id", studentId)
                .add("tags", tags)
                .add("class", formClass)
                .toString();
    }

}
