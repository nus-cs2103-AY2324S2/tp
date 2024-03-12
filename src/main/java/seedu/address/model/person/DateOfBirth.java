package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import seedu.address.commons.core.date.Date;

/**
 * Represents a Patient's Date of Birth in the CLInic.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateOfBirth(String)}
 */
public class DateOfBirth extends Date {
    public final LocalDate dob;

    /**
     * Constructs an {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth.
     */
    public DateOfBirth(String dateOfBirth) {
        super(dateOfBirth);
        checkArgument(isValidDateOfBirth(dateOfBirth), MESSAGE_CONSTRAINTS);
        this.dob = LocalDate.parse(dateOfBirth);
    }

    /**
     * Returns if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth);
        return dob.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        DateTimeFormatter dateformatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return dateformatter.format(this.dob);
    }
}
