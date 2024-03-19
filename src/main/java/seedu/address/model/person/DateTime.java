package seedu.address.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class DateTime {
    public static final String MESSAGE_CONSTRAINTS = "DateTime should be in the format yyyy-mm-dd hhmm and after current day and time";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
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
        value = dateTime;
    }

    public static boolean isValidDateTime(String test) {
        return test.matches(VALIDATION_REGEX) && isValidDateTimeFormat(test) && isAfterToday(test);
    }

    private static boolean isValidDateTimeFormat(String test) {
        try {
            formatter.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isAfterToday(String test) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime = LocalDateTime.parse(test, DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm"));
        return !dateTime.isBefore(now);
    }

    @Override
    public String toString() {
        return '[' + value + ']';
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

        DateTime dateTime = (DateTime) other;
        return value.equals(dateTime.value);
    }
}
