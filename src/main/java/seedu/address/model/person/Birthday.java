package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's telegram handle in the logbook.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS = "Birthday can take any values, and it should not be blank";

    /*
     * The first character of the birthday must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final DateTimeFormatter VALIDATION_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public final LocalDateTime value;
//    public final String value;

    /**
     * Constructs an {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
//        value = VALIDATION_FORMATTER.parse(birthday, LocalDateTime::from);
        value = LocalDateTime.parse(birthday, VALIDATION_FORMATTER);
//        value = birthday;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidBirthday(String test) {
        try {
            LocalDateTime.parse(test, VALIDATION_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
//        return true;
    }

    @Override
    public String toString() {
//        return value;
        return value.format(VALIDATION_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Birthday)) {
            return false;
        }

        Birthday otherBirthday = (Birthday) other;
        return value.equals(otherBirthday.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
