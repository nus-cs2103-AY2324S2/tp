package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's hobby in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidHobby(String)}
 */
public class Hobby {


    public static final String MESSAGE_CONSTRAINTS = "Hobby can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String hobby;

    /**
     * Constructs a {@code Hobby}.
     *
     * @param description A valid hobby.
     */
    public Hobby(String description) {
        requireNonNull(description);
        checkArgument(isValidHobby(description), MESSAGE_CONSTRAINTS);
        hobby = description;
    }

    /**
     * Returns true if a given string is a valid hobby.
     */
    public static boolean isValidHobby(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return hobby;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Hobby)) {
            return false;
        }

        Hobby otherHobby = (Hobby) other;
        return hobby.equals(otherHobby.hobby);
    }

    @Override
    public int hashCode() {
        return hobby.hashCode();
    }

}
