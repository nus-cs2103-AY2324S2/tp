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
    private final Dob dob;
    private final Ic ic;


    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final AdmissionDate admissionDate;
    private final Ward ward;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Set<Tag> tags,
                  Dob dob, Ic ic, AdmissionDate admissionDate, Ward ward) {
        requireAllNonNull(name, dob, ic, admissionDate, ward);
        this.name = name;
        this.tags.addAll(tags);
        this.dob = dob;
        this.ic = ic;
        this.admissionDate = admissionDate;
        this.ward = ward;
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Dob getDob() {
        return dob;
    }
    public Ic getIc() {
        return ic;
    }
    public AdmissionDate getAdmissionDate() {
        return admissionDate;
    }
    public Ward getWard() {
        return ward;
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
                && tags.equals(otherPerson.tags)
                && dob.equals(otherPerson.dob)
                && ic.equals(otherPerson.ic)
                && admissionDate.equals(otherPerson.admissionDate)
                && ward.equals(otherPerson.ward);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags, dob, ic, admissionDate, ward);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("tags", tags)
                .add("dob", dob)
                .add("ic", ic)
                .add("admissionDate", admissionDate)
                .add("ward", ward)
                .toString();
    }

}
