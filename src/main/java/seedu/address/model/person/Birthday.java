package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import seedu.address.commons.util.DateUtil;

/**
 * Represents a Client's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday {
    public static final String MESSAGE_CONSTRAINTS = DateUtil.getMessageConstraintsForDateType("Birthday");
    public final LocalDate date;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        this.date = DateUtil.parseStringToDate(birthday);
    }

    /**
     * Returns true if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        if (!DateUtil.isValidDateString(test)) {
            return false;
        }
        LocalDate birthdayTest = DateUtil.parseStringToDate(test);
        if (!DateUtil.isPastDate(birthdayTest)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return DateUtil.parseDateToString(date);
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
        return date.equals(otherBirthday.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
