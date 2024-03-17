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
    private final Name name;
    private final Set<Tag> tags = new HashSet<>();
    private Phone phone;
    private Email email;
    private Sex sex;
    // Data fields
    private Address address;
    private Allergies allergies;
    private BloodType bloodType;
    private Country country;
    private final DateOfBirth dateOfBirth;
    //Medical history
    private Condition condition;
    private DateOfAdmission dateOfAdmission;
    private Diagnosis diagnosis;
    private final Status status;
    private Symptom symptom;
    /**
     * Every field must be present and not null.
     */
    public Person(Nric nric, Name name, Phone phone, Address address, DateOfBirth dateOfBirth, Sex sex, Status status,
                  Email email, Allergies allergies, BloodType bloodType, Country country, Condition condition,
                  DateOfAdmission dateOfAdmission, Diagnosis diagnosis, Symptom symptom, Object... args) {
        //Only the fields that are mandatory are included down here
        requireAllNonNull(nric, name, phone, address, dateOfBirth, sex);
        this.nric = nric;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.sex = sex;

        this.address = address;
        this.allergies = allergies;
        this.bloodType = bloodType;
        this.country = country;
        this.dateOfBirth = dateOfBirth;

        this.condition = condition;
        this.dateOfAdmission = dateOfAdmission;
        this.diagnosis = diagnosis;
        this.status = status;
        this.symptom = symptom;
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

    public Sex getSex() {
        return this.sex;
    }

    public Address getAddress() {
        return address;
    }

    public Allergies getAllergies() {
        return allergies;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public Country getCountry() {
        return country;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public Condition getCondition() {
        return condition;
    }

    public DateOfAdmission getDateOfAdmission() {
        return dateOfAdmission;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public Status getStatus() {
        return this.status;
    }

    public Symptom getSymptom() {
        return symptom;
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
        return nric.equals(otherPerson.nric)
                && name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && address.equals(otherPerson.address)
                && dateOfBirth.equals(otherPerson.dateOfBirth)
                && dateOfAdmission.equals(otherPerson.dateOfAdmission)
                && sex.equals(otherPerson.sex);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        // list view
        return new ToStringBuilder(this).add("nric", nric).add("name", name).add("status", status).toString();
    }

    public String toDetailedString() {
        // detailed view
        return new ToStringBuilder(this)
                .add("nric", nric)
                .add("name", name)
                .add("tags", tags)
                .add("phone", phone)
                .add("email", email)
                .add("sex", sex)
                .add("address", address)
                .add("allergies", allergies)
                .add("blood type", bloodType)
                .add("country", country)
                .add("birthday", dateOfBirth)
                .add("condition", dateOfAdmission)
                .add("diagnosis", diagnosis)
                .add("status", status)
                .add("symptom", symptom)
                .toString();
    }
}
