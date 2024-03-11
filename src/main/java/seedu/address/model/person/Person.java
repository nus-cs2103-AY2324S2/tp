package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.ArrayList;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields

    private final EntryList entryList = new EntryList();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        entryList.add(name);
        this.tags.addAll(tags);
    }

    public void addEntry(Entry entry) {
        entryList.add(entry);
    }

    public Name getName() {
        Name name = (Name) entryList.get("Name");
        return name;
    }

    public Phone getPhone() {
        Phone phone = (Phone) entryList.get("Phone");
        return phone;
    }

    public Email getEmail() {
        Email email = (Email) entryList.get("Email");
        return email;
    }

    public Address getAddress() {
        Address address = (Address) entryList.get("Address");
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && otherPerson.getName().equals(getName());
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
        return getName().equals(otherPerson.getName())
                && getPhone().equals(otherPerson.getPhone())
                && getEmail().equals(otherPerson.getEmail())
                && getAddress().equals(otherPerson.getAddress())
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("phone", getPhone())
                .add("email", getEmail())
                .add("address", getAddress())
                .add("tags", tags)
                .toString();
    }

}
