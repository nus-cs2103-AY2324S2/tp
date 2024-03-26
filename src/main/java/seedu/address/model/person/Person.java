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
    private final Major major;
    private final Star star;
    private final Bolt bolt;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Major major, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, major, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.major = major;
        this.star = Star.NO_STAR;
        this.bolt = Bolt.NO_BOLT;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null. Alternative constructor to instantiate a Person
     * with an existing star and bolt.
     */
    public Person(Name name, Phone phone, Email email, Major major, Star star, Bolt bolt, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, major, star, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.major = major;
        this.star = star;
        this.bolt = bolt;
        this.tags.addAll(tags);
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

    public Major getMajor() {
        return major;
    }

    public Star getStar() {
        return star;
    } // get the stars

    public Bolt getBolt() {
        return bolt;
    } // get the bolts

    public int getStarCount() {
        return star.numOfStars;
    } //get the star count

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
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && major.equals(otherPerson.major)
                && star.equals(otherPerson.star)
                && bolt.equals(otherPerson.bolt)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, major, star, bolt, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("major", major)
                .add("star", star)
                .add("bolt", bolt)
                .add("tags", tags)
                .toString();
    }

}
