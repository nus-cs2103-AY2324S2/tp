package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.exceptions.AttributeNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagSet;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {
    /**
     * Attributes of a Person
     */
    public enum PersonAttribute {
        NAME,
        PHONE,
        EMAIL,
        ADDRESS,
        TAGS,
        NOTE
    }

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final TagSet tags;
    private final Note note;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Note note, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        Set<Tag> tagSet = new HashSet<>();
        tagSet.addAll(tags);
        this.tags = new TagSet(tagSet);
        this.note = note;
    }

    /**
     * Get the valued of the specified attribute.
     *
     * @param attribute Attribute to retrieve
     *
     * @return Value of the specified attribute
     */
    public Attribute<? extends Object> getAttribute(PersonAttribute attribute) {
        switch (attribute) {
        case NAME:
            return this.name;
        case PHONE:
            return this.phone;
        case EMAIL:
            return this.email;
        case ADDRESS:
            return this.address;
        case TAGS:
            return this.tags;
        case NOTE:
            return this.note;
        default:
            throw new AttributeNotFoundException();
        }
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
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return this.tags.getValue();
    }

    public Note getNote() {
        return this.note;
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
                && otherPerson.getAttribute(PersonAttribute.NAME).equals(getAttribute(PersonAttribute.NAME));
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
                && tags.equals(otherPerson.tags)
                && note.equals(otherPerson.note);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("note", note)
                .add("tags", tags)
                .toString();
    }

}
