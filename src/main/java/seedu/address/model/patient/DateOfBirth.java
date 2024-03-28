package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Patient's date of birth.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateOfBirth(String)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS =
            "Date of birth should only contain numeric values in dd/MM/yyyy or yyyy-MM-dd format, and it should not "
                    + "be left blank";

    public static final DateTimeFormatter INPUTFORMAT1 = DateTimeFormatter.ofPattern("d/M/yyyy");
    public static final DateTimeFormatter INPUTFORMAT2 = DateTimeFormatter.ofPattern("yyyy-M-d");
    public static final DateTimeFormatter OUTPUTFORMAT = DateTimeFormatter.ofPattern("yyyy-M-d");
    public final LocalDate dateOfBirth;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth.
     */
    public DateOfBirth(String dateOfBirth) {
        requireNonNull(dateOfBirth);
        checkArgument(isValidDateOfBirth(dateOfBirth), MESSAGE_CONSTRAINTS);
        this.dateOfBirth = parseDateOfBirth(dateOfBirth);
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String test) {
        return parseDateOfBirth(test) == null ? false : true;
    }

    /**
     * Parse String date of birth into LocalDate.
     *
     * @param dateOfBirthString date of birth input.
     * @return date of birth in LocalDate type.
     */
    public static LocalDate parseDateOfBirth(String dateOfBirthString) {
        String cleanDateString = dateOfBirthString.strip();
        DateTimeFormatter inputFormat = determineInputFormat(cleanDateString);
        LocalDate dateOfBirth = null;

        try {
            dateOfBirth = LocalDate.parse(cleanDateString, inputFormat);
            return dateOfBirth;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Determines in which format the date of birth is input.
     *
     * @return the input format of String date of birth.
     */
    private static DateTimeFormatter determineInputFormat(String dateOfBirth) {
        boolean isInputFromat1 = dateOfBirth.split("/").length > 1;
        return isInputFromat1 ? INPUTFORMAT1 : INPUTFORMAT2;
    }

    @Override
    public String toString() {
        return dateOfBirth.format(OUTPUTFORMAT);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateOfBirth)) {
            return false;
        }

        DateOfBirth otherDateOfBirth = (DateOfBirth) other;
        return dateOfBirth.equals(otherDateOfBirth.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return dateOfBirth.hashCode();
    }
}
