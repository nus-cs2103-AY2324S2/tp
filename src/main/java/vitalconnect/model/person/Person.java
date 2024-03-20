package vitalconnect.model.person;

import static vitalconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import vitalconnect.commons.util.ToStringBuilder;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;
import vitalconnect.model.person.medicalinformation.MedicalInformation;

/**
 * Represents a Person in the clinic.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    // Information fields
    private IdentificationInformation identificationInformation;
    private ContactInformation contactInformation;
    private MedicalInformation medicalInformation;

    /**
     * Only identificationInformation is present.
     */
    public Person(IdentificationInformation identificationInformation) {
        requireAllNonNull(identificationInformation);
        this.identificationInformation = identificationInformation;
        this.contactInformation = new ContactInformation();
        this.medicalInformation = new MedicalInformation();
    }

    /**
     * Only identificationInformation and contactInformation are present.
     */
    public Person(IdentificationInformation identificationInformation,
                  ContactInformation contactInformation) {
        requireAllNonNull(identificationInformation);
        this.identificationInformation = identificationInformation;
        this.contactInformation = contactInformation;
        this.medicalInformation = new MedicalInformation();
    }

    /**
     * Only identificationInformation and medicalInformation are present.
     */
    public Person(IdentificationInformation identificationInformation,
                  MedicalInformation medicalInformation) {
        requireAllNonNull(identificationInformation);
        this.identificationInformation = identificationInformation;
        this.contactInformation = new ContactInformation();
        this.medicalInformation = medicalInformation;
    }

    /**
     * All fields are present.
     */
    public Person(IdentificationInformation identificationInformation,
                  ContactInformation contactInformation, MedicalInformation medicalInformation) {
        requireAllNonNull(identificationInformation, contactInformation, medicalInformation);
        this.identificationInformation = identificationInformation;
        this.contactInformation = contactInformation;
        this.medicalInformation = medicalInformation;
    }

    public IdentificationInformation getIdentificationInformation() {
        return this.identificationInformation;
    }
    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public ContactInformation getContactInformation() {
        return this.contactInformation;
    }

    public boolean hasContactInformation() {
        return this.contactInformation != null;
    }

    public void setMedicalInformation(MedicalInformation medicalInformation) {
        this.medicalInformation = medicalInformation;
    }

    public MedicalInformation getMedicalInformation() {
        return this.medicalInformation;
    }

    public boolean hasMedicalInformation() {
        return !this.medicalInformation.isEmpty();
    }


    /**
     * Returns true if both persons have the same identification info.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson
                .getIdentificationInformation()
                .getNric()
                .equals(getIdentificationInformation().getNric());
    }

    /**
     * Make a new copy of that person.
     */
    public Person copyPerson() {
        if (hasContactInformation() && hasMedicalInformation()) {
            return new Person(this.identificationInformation, this.contactInformation, this.medicalInformation);
        } else if (hasContactInformation()) {
            return new Person(this.identificationInformation, this.contactInformation);
        } else if (hasMedicalInformation()) {
            return new Person(this.identificationInformation, this.medicalInformation);
        } else {
            return new Person(this.identificationInformation);
        }
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
        boolean isSamePerson = getIdentificationInformation().equals(otherPerson.getIdentificationInformation());

        if (otherPerson.hasContactInformation() && hasContactInformation()) {
            // true if both have same contact information
            isSamePerson = isSamePerson && getContactInformation().equals(otherPerson.getContactInformation());
        } else {
            // true if both do not have contact information
            isSamePerson = isSamePerson && !otherPerson.hasContactInformation() && !hasContactInformation();
        }

        if (otherPerson.hasMedicalInformation() && hasMedicalInformation()) {
            // true if both have same medical information
            isSamePerson = isSamePerson && getMedicalInformation().equals(otherPerson.getMedicalInformation());
        } else {
            // true if both do not have medical information
            isSamePerson = isSamePerson && !otherPerson.hasMedicalInformation() && !hasMedicalInformation();
        }

        return isSamePerson;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getIdentificationInformation(), getContactInformation(), getMedicalInformation());
    }

    @Override
    public String toString() {
        if (hasContactInformation() && hasMedicalInformation()) {
            return new ToStringBuilder(this)
                    .add("identification", getIdentificationInformation())
                    .add("contact", getContactInformation())
                    .add("medicalinfo", getMedicalInformation())
                    .toString();
        } else if (hasContactInformation()) {
            return new ToStringBuilder(this)
                    .add("identification", getIdentificationInformation())
                    .add("contact", getContactInformation())
                    .toString();
        } else if (hasMedicalInformation()) {
            return new ToStringBuilder(this)
                    .add("identification", getIdentificationInformation())
                    .add("medicalinfo", getMedicalInformation())
                    .toString();
        } else {
            return new ToStringBuilder(this)
                    .add("identification", getIdentificationInformation())
                    .toString();
        }
    }

}
