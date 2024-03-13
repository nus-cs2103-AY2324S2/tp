package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.tag.Tag;

import java.util.*;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    private final UUID uuid;

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final HashMap<String, Attribute> attributes = new HashMap<>();

    /**
     * Constructs a person with a random UUID.
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.uuid = UUID.randomUUID();
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
    /**
     * Returns the uuid of the person.
     *
     * @return The uuid of the person.
     */
    public UUID getUuid() {
        return uuid;
    }
    /**
     * Returns the uuid of the person as a string.
     *
     * @return The uuid of the person as a string.
     */
    public String getUuidString() {
        return uuid.toString();
    }
    /**
     * Returns whether the person has an attribute with the given type.
     *
     * @param attributeName The type of the attribute to check for.
     * @return True if the person has an attribute with the given type.
     */
    public boolean hasAttribute(String attributeName) {
        return attributes.containsKey(attributeName);
    }
    /**
     * Returns an attribute of the person.
     *
     * @param attributeName The type of the attribute to get.
     * @return The attribute with the given type.
     */
    public Attribute getAttribute(String attributeName) {
        assertValidAttributeName(attributeName);
        return attributes.get(attributeName);
    }
    /**
     * Edits/adds an attribute of/to the person.
     *
     * @param attribute The attribute to edit or add.
     */
    public void updateAttribute(Attribute attribute) {
        assertValidAttribute(attribute);
        attributes.put(attribute.getName(), attribute);
    }

    /**
     * Deletes an attribute from the person.
     *
     * @param attributeType The type of the attribute to delete.
     */
    public void deleteAttribute(String attributeType) {
        if (!attributes.containsKey(attributeType)) {
            throw new IllegalArgumentException("Attribute with name " + attributeType + " does not exist");
        }
        attributes.remove(attributeType);
    }

    private static void assertValidAttribute(Attribute attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Attribute cannot be null.");
        }
        assertValidAttributeName(attribute.getName());
    }

    private static void assertValidAttributeName(String attributeType) {
        if (attributeType == "") {
            throw new IllegalArgumentException("Attribute name cannot be empty.");
        }
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
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }
    /**
     * Returns true if the UUID of the person is the same as the UUID of the other object.
     *
     * @param other The object to compare with.
     * @return True if the UUID of the person is the same as the UUID of the other object.
     */
    public boolean equalsUuid(Object other) {
        if (!(other instanceof Person)) {
            return false;
        }

        if (other == this) {
            return true;
        }

        Person otherPerson = (Person) other;
        return uuid.equals(otherPerson.uuid);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, uuid);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("uuid", uuid)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
