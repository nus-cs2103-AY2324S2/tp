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
    private final Phone phone;
    private final Email email;
    //private String uniqueId;
    private Id uniqueId;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Subject subject;
    private final Payment payment;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Subject subject,
                  Id uniqueId, Payment payment) {
        requireAllNonNull(name, phone, email, address, tags, subject, payment);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.subject = subject;
        this.uniqueId = uniqueId;
        this.payment = payment;
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, Subject subject, Payment payment) {
        requireAllNonNull(name, phone, email, address, tags, subject);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.subject = subject;
        this.payment = payment;
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

    public Subject getSubject() {
        return subject;
    }

    public Payment getPayment() {
        return payment;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /** Returns the uniqueId of the person */
    public Id getUniqueId() {
        return uniqueId;
    }
    /** Sets the uniqueId of the person */
    public Id setUniqueId(Id uniqueId) {
        return this.uniqueId = uniqueId;
    }


    /**
     * Returns true if both persons have the same ID.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getUniqueId().equals(this.uniqueId);
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
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && subject.equals(otherPerson.subject)
                && tags.equals(otherPerson.tags)
                && payment.equals(otherPerson.payment);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, uniqueId, payment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("subject", subject)
                .add("tags", tags)
                .add("payment", payment)
                .toString();
    }

}
