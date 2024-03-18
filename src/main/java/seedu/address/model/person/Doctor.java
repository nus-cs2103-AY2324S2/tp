package seedu.address.model.person;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {

    /**
     * Every field must be present and not null.
     */
    public Doctor(Nric nric, Name name, DoB dob, Phone phone) {
        super(Type.DOCTOR, nric, name, dob, phone);
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
        if (!(other instanceof Doctor)) {
            return false;
        }

        Doctor otherPerson = (Doctor) other;
        return getNric().equals(otherPerson.getNric())
                && getName().equals(otherPerson.getName())
                && getDoB().equals(otherPerson.getDoB())
                && getPhone().equals(otherPerson.getPhone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(Type.DOCTOR, getNric(), getName(), getDoB(), getPhone());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("type", Type.DOCTOR)
                .add("nric", getNric())
                .add("name", getName())
                .add("dob", getDoB())
                .add("phone", getPhone())
                .toString();
    }

}
