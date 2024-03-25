package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.FreeTimeTag;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the logbook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final RoomNumber roomNumber;
    private final Telegram telegram;
    private final Birthday birthday;
    private final Set<FreeTimeTag> tags = new HashSet<>();
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, RoomNumber roomNumber, Telegram telegram, Birthday birthday, Set<FreeTimeTag> tags) {
        requireAllNonNull(name, phone);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.roomNumber = roomNumber;
        this.telegram = telegram;
        this.birthday = birthday;
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

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<FreeTimeTag> getTags() {
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

        boolean isEqual = name.equals(otherPerson.name) && phone.equals(otherPerson.phone);

        if (email != null && otherPerson.email == null || email == null && otherPerson.email != null) {
            return false;
        } else if (email != null && otherPerson.email != null) {
            isEqual = isEqual && email.equals(otherPerson.email);
        }

        if (roomNumber != null && otherPerson.roomNumber == null
                || roomNumber == null && otherPerson.roomNumber != null) {
            return false;
        } else if (roomNumber != null && otherPerson.roomNumber != null) {
            isEqual = isEqual && roomNumber.equals(otherPerson.roomNumber);
        }

        if (telegram != null && otherPerson.telegram == null || telegram == null && otherPerson.telegram != null) {
            return false;
        } else if (telegram != null && otherPerson.telegram != null) {
            isEqual = isEqual && telegram.equals(otherPerson.telegram);
        }

        if (birthday != null && otherPerson.birthday == null || birthday == null && otherPerson.birthday != null) {
            return false;
        } else if (birthday != null && otherPerson.birthday != null) {
            isEqual = isEqual && birthday.equals(otherPerson.birthday);
        }

        isEqual = isEqual && tags.equals(otherPerson.tags);

        return isEqual;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, roomNumber, telegram, birthday, tags);
    }

    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this);
        sb.add("name", name);
        sb.add("phone", phone);
        sb.add("tags", tags);

        if (email != null) {
            sb.add("email", email);
        }

        if (roomNumber != null) {
            sb.add("roomNumber", roomNumber);
        }

        if (telegram != null) {
            sb.add("telegram", telegram);
        }

        if (birthday != null) {
            sb.add("birthday", birthday);
        }

        return sb.toString();
    }

}
