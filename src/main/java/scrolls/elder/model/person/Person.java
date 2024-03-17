package scrolls.elder.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import scrolls.elder.commons.util.CollectionUtil;
import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    protected int id;
    protected final Name name;
    protected final Phone phone;
    protected final Email email;
    protected final Role role;

    // Data fields
    protected final Address address;
    protected final Set<Tag> tags = new HashSet<>();
    protected Person pairedWith;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Role role) {
        CollectionUtil.requireAllNonNull(name, phone, email, address, tags, role);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.role = role;
        this.pairedWith = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Person getPairedWith() {
        return pairedWith;
    }

    public void setPairedWith(Person pairedWith) {
        this.pairedWith = pairedWith;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getId() == this.getId();
    }

    /**
    * Returns true if person is a volunteer, and false if person is not a volunteer
    */
    public abstract boolean isVolunteer();
    public abstract Role getRole();

    public boolean hasSamePairing(Person otherPerson) {
        if (otherPerson == this || (pairedWith == null && otherPerson.pairedWith == null)) {
            return true;
        }

        if ((pairedWith == null) ^ (otherPerson.pairedWith == null)) {
            return false;
        }

        return pairedWith.equals(otherPerson.pairedWith);
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
        return id == otherPerson.id
                && name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && role.equals(otherPerson.role)
                && hasSamePairing(otherPerson);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, name, phone, email, address, tags, role, pairedWith);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", id)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("role", role)
                .add("pairedWith", pairedWith == null ? "None" : pairedWith.getName())
                .toString();
    }

}
