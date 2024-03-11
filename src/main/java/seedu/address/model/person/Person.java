package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    private final Type type;
    private final Nric nric;
    private final Name name;
    private final DoB dob;
    private final Phone phone;

    /**
     * Every field must be present and not null.
     */
    public Person(Type type, Nric nric, Name name, DoB dob, Phone phone) {
        requireAllNonNull(type, nric, name, dob, phone);
        this.type = type;
        this.nric = nric;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
    }

    public Type getType() {
        return type;
    }
    public Nric getNric() {
        return nric;
    }
    public Name getName() {
        return name;
    }
    public DoB getDoB() {
        return dob;
    }
    public Phone getPhone() {
        return phone;
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
                && otherPerson.getNric().equals(getNric());
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
        return type.equals(otherPerson.type)
                && nric.equals(otherPerson.nric)
                && name.equals(otherPerson.name)
                && dob.equals(otherPerson.dob)
                && phone.equals(otherPerson.phone);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(type, nric, name, dob, phone);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("type", type)
                .add("nric", nric)
                .add("name", name)
                .add("dob", dob)
                .add("phone", phone)
                .toString();
    }

}
