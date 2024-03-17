package vitalconnect.model.person;

import static vitalconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import vitalconnect.commons.util.ToStringBuilder;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;
import vitalconnect.model.tag.Tag;

/**
 * Represents a Person in the clinic.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    // Information fields
    private final IdentificationInformation identificationInformation;
    private final ContactInformation contactInformation;

    // Data fields`
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(IdentificationInformation identificationInformation, Set<Tag> tags) {
        requireAllNonNull(identificationInformation, tags);
        this.identificationInformation = identificationInformation;
        this.contactInformation = null;

        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Person(IdentificationInformation identificationInformation,
                  ContactInformation contactInformation, Set<Tag> tags) {
        requireAllNonNull(identificationInformation, contactInformation, tags);
        this.identificationInformation = identificationInformation;
        this.contactInformation = contactInformation;

        this.tags.addAll(tags);
    }

    public IdentificationInformation getIdentificationInformation() {
        return this.identificationInformation;
    }

    public ContactInformation getContactInformation() {
        return this.contactInformation;
    }

    public boolean hasContactInformation() {
        return this.contactInformation != null;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
            && otherPerson.getIdentificationInformation().getNric().equals(getIdentificationInformation().getNric());
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

        // If both have contact information
        if (otherPerson.hasContactInformation() && hasContactInformation()) {
            return getIdentificationInformation().equals(otherPerson.getIdentificationInformation())
                && getContactInformation().equals(otherPerson.getContactInformation())
                && tags.equals(otherPerson.tags);
        }

        // If neither has contact information
        if (!otherPerson.hasContactInformation() && !hasContactInformation()) {
            return getIdentificationInformation().equals(otherPerson.getIdentificationInformation())
                && tags.equals(otherPerson.tags);
        }

        return false;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getIdentificationInformation(), getContactInformation(), tags);
    }

    @Override
    public String toString() {
        if (contactInformation != null) {
            return new ToStringBuilder(this)
                    .add("identification", getIdentificationInformation())
                    .add("contact", getContactInformation())
                    .add("tags", tags)
                    .toString();
        } else {
            return new ToStringBuilder(this)
                    .add("identification", getIdentificationInformation())
                    .add("tags", tags)
                    .toString();
        }
    }

}
