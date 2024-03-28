package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a dateTime in the address book.
 * Guarantees: immutable; dateTime is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {
    public static final String MESSAGE_CONSTRAINTS = "DateTime should be in the format yyyy-mm-dd hhmm";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
            .withResolverStyle(ResolverStyle.STRICT);

    public final String value;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid dateTime.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.value = dateTime;
    }

    public static boolean isValidDateTime(String test) {
        return test.matches(VALIDATION_REGEX) && isValidDateTimeFormat(test);
    }

    private static boolean isValidDateTimeFormat(String test) {
        try {
            FORMATTER.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateTime)) {
            return false;
        }

        DateTime otherDateTime = (DateTime) other;
        return value.equals(otherDateTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + value + ']';
    }
}
