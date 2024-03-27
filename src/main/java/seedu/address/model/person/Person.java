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

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Note note;
    private final Rating rating;
    private Pin pin;
    private boolean hasDeadlineNote;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Note note, Set<Tag> tags, Rating rating) {
        requireAllNonNull(name, phone, email, address, note, tags, rating);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.note = note;
        this.pin = new Pin();
        this.tags.addAll(tags);
        this.rating = rating;
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

    public Note getNote() {
        return note;
    }
    public Rating getRating() {
        return rating;
    }

    /**
     * Returns a new instantiation of the current {@code Person} with the updated note,
     * which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    public Person updateNote(Note note) {
        Person personToReturn = new Person(this.name, this.phone, this.email, this.address, note, this.tags,
                this.rating);
        personToReturn.setPinIfPinned(this);
        return personToReturn;
    }

    /**
     * Returns a new instantiation of the current {@code Person} with the updated rating,
     * which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    public Person updateRating(Rating rating) {
        Person personToReturn = new Person(this.name, this.phone, this.email, this.address, this.note, this.tags,
                rating);
        personToReturn.setPinIfPinned(this);
        return personToReturn;
    }

    public Pin getPin() {
        return this.pin;
    }

    public void toPin() {
        pin.setPin();
    }

    public void toUnpin() {
        pin.setUnpin();
    }

    public boolean isPinned() {
        return pin.getIsPinned();
    }

    public void setPinIfPinned(Person person) {
        if (person.isPinned()) {
            this.toPin();
        }
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
     * Name is case-insensitive, Janna and janna is same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    public void setNoteContent(String content) {
        this.note.setValue(content);
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

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, note, tags, rating);
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
                .add("rating", rating)
                .toString();
    }
}
