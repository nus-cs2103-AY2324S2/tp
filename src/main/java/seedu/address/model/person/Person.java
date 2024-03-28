package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.FreeTimeTag;

/**
 * Represents a Person in the logbook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    private static final Logger logger = LogsCenter.getLogger(Person.class);


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
    public Person(Name name, Phone phone, Email email, RoomNumber roomNumber, Telegram telegram, Birthday birthday,
                  Set<FreeTimeTag> tags) {
        requireAllNonNull(name, phone, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.roomNumber = roomNumber;
        this.telegram = telegram;
        this.birthday = birthday;
        this.tags.addAll(tags);
    }

    public Name getName() {
        logger.log(Level.INFO, "Retrieved person's name");
        return name;
    }

    public Phone getPhone() {
        logger.log(Level.INFO, "Retrieved person's phone number");
        return phone;
    }

    public Email getEmail() {
        logger.log(Level.INFO, "Retrieved person's email");
        return email;
    }

    public RoomNumber getRoomNumber() {
        logger.log(Level.INFO, "Retrieved person's room number");
        return roomNumber;
    }

    public Telegram getTelegram() {
        logger.log(Level.INFO, "Retrieved person's telegram");
        return telegram;
    }

    public Birthday getBirthday() {
        logger.log(Level.INFO, "Retrieved person's birthday");
        return birthday;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<FreeTimeTag> getTags() {
        logger.log(Level.INFO, "Retrieved person's free time tags");
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
