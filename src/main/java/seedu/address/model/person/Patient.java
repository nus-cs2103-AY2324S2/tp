package seedu.address.model.person;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {

    /**
     * Every field must be present and not null.
     */
    public Patient(Nric nric, Name name, DoB dob, Phone phone) {
        super(Type.PATIENT, nric, name, dob, phone);
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

        Patient otherPerson = (Patient) other;
        return getNric().equals(otherPerson.getNric())
                && getName().equals(otherPerson.getName())
                && getDoB().equals(otherPerson.getDoB())
                && getPhone().equals(otherPerson.getPhone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(Type.PATIENT, getNric(), getName(), getDoB(), getPhone());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("type", Type.PATIENT)
                .add("nric", getNric())
                .add("name", getName())
                .add("dob", getDoB())
                .add("phone", getPhone())
                .toString();
    }

}
