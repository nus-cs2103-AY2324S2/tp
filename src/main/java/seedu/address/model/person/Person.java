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
    //Mandatory fields
    //Identity fields
    private final Nric nric;
    private Name name;
    private Phone phone;
    private Address address;
    private final DateOfBirth dateOfBirth;
    private Sex sex;
    private Status status;
    // Optional fields
    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private Email email = null;
    private Country country = null;
    //Medical information
    private Allergies allergies = null;
    private BloodType bloodType = null;
    private Condition condition = null;
    private DateOfAdmission dateOfAdmission = null;
    private Diagnosis diagnosis = null;
    private Symptom symptom = null;
    /**
     * Every mandatory field must be present and not null.
     */
    public Person(Nric nric, Name name, Phone phone, Address address, DateOfBirth dateOfBirth, Sex sex, Status status) {
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
     * Sets the name of the person to input value.
     * @param name the new name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Sets the phone of the person to input value.
     * @param phone the new phone.
     */
    public void setPhone(Phone phone) {
        this.phone = phone;
    }
    /**
     * Sets the address of the person to input value.
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    /**
     * Sets the sex of the person to input value.
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }
    /**
     * Sets the status of the person to input value.
     * @param status the new status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    /**
     * Sets the email of the person to input value.
     * @param email the new email.
     */
    public void setEmail(Email email) {
        this.email = email;
    }
    /**
     * Sets the allergies of the person to input value.
     * @param allergies the new allergies.
     */
    public void setAllergies(Allergies allergies) {
        this.allergies = allergies;
    }
    /**
     * Sets the blood type of the person to input value.
     * @param bloodType the new blood type.
     */
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
    /**
     * Sets the country of the person to input value.
     * @param country the new country.
     */
    public void setCountry(Country country) {
        this.country = country;
    }
    /**
     * Sets the condition of the person to input value.
     * @param condition the new condition.
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    /**
     * Sets the date of admission of the person to input value.
     * @param dateOfAdmission the new date of admission.
     */
    public void setDateOfAdmission(DateOfAdmission dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }
    /**
     * Sets the diagnosis of the person to input value.
     * @param diagnosis the new diagnosis.
     */
    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
    /**
     * Sets the symptom of the person to input value.
     * @param symptom the new symptom.
     */
    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
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
     * Returns true if both persons have the same identity and all data fields.
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
                && sex.equals(otherPerson.sex)
                && status.equals(otherPerson.status)
                && tags.equals(otherPerson.tags)
                && email.equals(otherPerson.email)
                && country.equals(otherPerson.country)
                && allergies.equals(otherPerson.allergies)
                && bloodType.equals(otherPerson.bloodType)
                && condition.equals(otherPerson.condition)
                && dateOfAdmission.equals(otherPerson.dateOfAdmission)
                && diagnosis.equals(otherPerson.diagnosis)
                && symptom.equals(otherPerson.symptom);
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

    /**
     * @return Detailed String of Person
     */
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
