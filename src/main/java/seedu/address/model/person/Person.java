package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.note.Note;
import seedu.address.model.person.tag.Tag;

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
    private final ObservableList<Note> notes = FXCollections.observableArrayList();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, ObservableList<Note> notes) {
        requireAllNonNull(name, phone, email, address, tags, notes);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.notes.addAll(notes);
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
     * Returns an immutable notes list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableList<Note> getNotes() {
        return FXCollections.unmodifiableObservableList(notes);
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
            && tags.equals(otherPerson.tags)
            && notes.equals(otherPerson.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address, tags, notes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("name", name)
            .add("phone", phone)
            .add("email", email)
            .add("address", address)
            .add("tags", tags)
            .add("notes", notes)
            .toString();
    }

    /**
     * Represents a builder for a {@link Person}.
     */
    public static class Builder {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags = new HashSet<>();
        private ObservableList<Note> notes = FXCollections.observableArrayList();

        /**
         * Creates a {@code Builder} with the given parameters.
         */
        public Builder(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                       ObservableList<Note> notes) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.address = address;
            this.tags.addAll(tags);
            this.notes.addAll(notes);
        }

        /**
         * Initializes the {@code Builder} with the data of {@code person}.
         */
        public Builder(Person person) {
            this(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), person.getTags(),
                person.getNotes());
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

        public Set<Tag> getTags() {
            return tags;
        }

        public ObservableList<Note> getNotes() {
            return notes;
        }

        public Builder setName(Name name) {
            requireNonNull(name);

            this.name = name;
            return this;
        }

        public Builder setPhone(Phone phone) {
            requireNonNull(phone);

            this.phone = phone;
            return this;
        }

        public Builder setEmail(Email email) {
            requireNonNull(email);

            this.email = email;
            return this;
        }

        public Builder setAddress(Address address) {
            requireNonNull(address);

            this.address = address;
            return this;
        }

        public Builder setTags(Set<Tag> tags) {
            requireNonNull(tags);

            this.tags.clear();
            this.tags.addAll(tags);
            return this;
        }

        public Builder setNotes(ObservableList<Note> notes) {
            requireNonNull(notes);

            this.notes.clear();
            this.notes.addAll(notes);
            return this;
        }

        /**
         * Builds a {@link Person} with the latest values.
         */
        public Person build() {
            return new Person(name, phone, email, address, tags, notes);
        }

    }
}
