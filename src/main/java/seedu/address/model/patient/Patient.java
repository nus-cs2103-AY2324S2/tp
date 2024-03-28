package seedu.address.model.patient;

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
public class Patient {

    private static int idTracker = 1;

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    private final int id;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Set<Tag> tags) {
        this(name, phone, email, tags, idTracker);
    }


    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Set<Tag> tags, int studentId) {
        requireAllNonNull(name, phone, email, tags, studentId);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tags.addAll(tags);
        this.id = studentId;
        // When we are recreating Person with the Id, the id tracker also keeps note of the next id.
        idTracker = this.id + 1;
    }


    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Set<Tag> tags, int studentId, boolean updatedPerson) {
        // TODO: This method needs a rewrite in v1.3
        // The constructor has an additional boolean variable to make sure that the Java compiler treats
        // it as a totally different constructor.
        // The main difference with (Name name, Phone phone, Email email, Set<Tag> tags, int studentId)
        // is that it does not refresh the idTracker variable. The constructor without the `updatedPerson` is used
        // to refresh the master last used Id from the last Id of the JSON file.
        requireAllNonNull(name, phone, email, tags, studentId);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tags.addAll(tags);
        this.id = studentId;
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

    public int getSid() {
        return id;
    }

    public static int getIdTracker() {
        return Patient.idTracker;
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
    public boolean isSamePerson(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getName().equals(getName());
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
        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return name.equals(otherPatient.name)
                && phone.equals(otherPatient.phone)
                && email.equals(otherPatient.email)
                // && id == otherPerson.id
                && tags.equals(otherPatient.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags, id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("tags", tags)
                .add("id", id)
                .toString();
    }

}
