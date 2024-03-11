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
    private final ClassGroup classGroup;
    private final Email email;
    private final Phone phone;
    private final Telegram telegram;
    private final Github github;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, ClassGroup classGroup, Email email, Phone phone, Telegram telegram, Github github) {
        requireAllNonNull(name, classGroup, email, phone);
        this.name = name;
        this.classGroup = classGroup;
        this.email = email;
        this.phone = phone;
        this.telegram = telegram;
        this.github = github;
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

    public ClassGroup getClassGroup() {
        return classGroup;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public Github getGithub() {
        return github;
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
                && classGroup.equals(otherPerson.classGroup)
                && email.equals(otherPerson.email)
                && phone.equals(otherPerson.phone)
                && telegram.equals(otherPerson.telegram)
                && github.equals(otherPerson.github);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, classGroup, email, phone, telegram, github);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("classGroup", classGroup)
                .add("email", email)
                .add("phone", phone)
                .add("telegram", telegram)
                .add("github", github)
                .toString();
    }
}
