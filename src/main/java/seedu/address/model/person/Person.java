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
    private final IdentityCardNumber identityCardNumber;

    // Data fields
    private final Age age;
    private final Sex sex;
    private final Address address;
    private final Note note;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, IdentityCardNumber identityCardNumber, Age age, Sex sex, Address address,
                  Note note, Set<Tag> tags) {
        requireAllNonNull(name, phone, identityCardNumber, age, sex, address, tags);
        this.name = name;
        this.phone = phone;
        this.identityCardNumber = identityCardNumber;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.note = note;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public IdentityCardNumber getIdentityCardNumber() {
        return identityCardNumber;
    }

    public Age getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public Address getAddress() {
        return address;
    }

    public Note getNote() {
        return note;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name AND IC number.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getIdentityCardNumber().equals(getIdentityCardNumber());
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
                && identityCardNumber.equals(otherPerson.identityCardNumber)
                && age.equals(otherPerson.age)
                && sex.equals(otherPerson.sex)
                && address.equals(otherPerson.address)
                && note.equals(otherPerson.note)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, identityCardNumber, age, sex, address, note, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("identityCardNumber", identityCardNumber)
                .add("age", age)
                .add("sex", sex)
                .add("address", address)
                .add("note", note)
                .add("tags", tags)
                .toString();
    }

}
