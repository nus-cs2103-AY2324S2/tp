package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.core.date.Date;

/**
 * Represents a Patient's Date of Birth in the CLInic.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateOfBirth(String)}
 */
public class DateOfBirth extends Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Date of birth must be before today's date";

    /**
     * Constructs an {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth (before today).
     */
    public DateOfBirth(String dateOfBirth) {
        super(dateOfBirth);
        checkArgument(isValidDateOfBirth(dateOfBirth), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String dateOfBirth) {
        return Date.isBeforeToday(dateOfBirth);
    }
}
