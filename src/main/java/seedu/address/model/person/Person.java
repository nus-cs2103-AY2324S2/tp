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

    private final Nric nric;
    private final Set<Tag> tags = new HashSet<>();
    private final Name name;
    private final Phone phone;
    private Email email;
    private final Sex sex;
    // Data fields
    private final Address address;
    private Allergies allergies;
    private BloodType bloodType;
    private Country country;
    private final DateOfBirth dateOfBirth;
    //Medical history
    private Diagnosis diagnosis;
    private Condition condition;
    private Symptom symptom;
    private DateOfAdmission dateOfAdmission;
    private final Status status;
    /**
     * Every field must be present and not null.
     */
    //TODO : Add the missing fields
    public Person(Nric nric, Name name, Phone phone, Address address, DateOfBirth dateOfBirth, Sex sex, Status status, Object... args) {
        //Only the fields that are mandatory are included down here
        requireAllNonNull(nric, name, phone, address, dateOfBirth, sex);
        this.nric = nric;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.status = status;
    }

    public Nric getNric() {
        return nric;
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

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public Sex getSex() {
        return this.sex;
    }

    public Status getStatus() {
        return this.status;
    }

    public DateOfAdmission getDateOfAdmission() {
        return dateOfAdmission;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same nric.
     * This defines a weaker notion of equality between two persons.
     */
    //TODO : change unique field to NRIC
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getNric().equals(getNric());
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
        return nric.equals(otherPerson.nric) && name.equals(otherPerson.name) && phone.equals(otherPerson.phone) && address.equals(otherPerson.address) && dateOfBirth.equals(otherPerson.dateOfBirth) && dateOfAdmission.equals(otherPerson.dateOfAdmission) && sex.equals(otherPerson.sex);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    //TODO : Add the missing fields
    //TODO : Add override: one for list view, one for detailed view
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", name).add("phone", phone).add("address", address).add("tags", tags).toString();
    }

}
