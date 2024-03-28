package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.group.Group;

/**
 * Represents a Person in the major book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Year year;
    private final Telegram telegram;

    // Data fields
    private final Major major;
    private final Remark remark;
    private final Set<Group> groups = new HashSet<>();

    /**
     * Every field must be present and not null.
     */

    public Person(Name name, Phone phone, Email email, Year year, Telegram telegram, Major major, Remark remark,
                  Set<Group> groups) {
        requireAllNonNull(name, phone, email, major, remark, groups);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.year = year;
        this.major = major;
        this.telegram = telegram;
        this.remark = remark;
        this.groups.addAll(groups);
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
    public Year getYear() {
        return year;
    }
    public Telegram getTelegram() {
        return telegram;
    }
    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable group set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
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
                && year.equals(otherPerson.year)
                && telegram.equals(otherPerson.telegram)
                && major.equals(otherPerson.major)
                && remark.equals(otherPerson.remark)
                && groups.equals(otherPerson.groups);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, year, telegram, major, remark, groups);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("year", year)
                .add("telegram", telegram)
                .add("major", major)
                .add("remark", remark)
                .add("groups", groups)
                .toString();
    }

}
