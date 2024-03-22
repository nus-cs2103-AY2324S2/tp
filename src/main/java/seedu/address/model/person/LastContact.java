package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a client's last contacted date and time in the address book.
 */
public class LastContact {

    public static final String MESSAGE_CONSTRAINTS = "Expected DATETIME format: DD-MM-YYYY HHmm\n"
                                                    + "Actual format: %s";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm")
            .withResolverStyle(ResolverStyle.STRICT);
    private LocalDateTime dateTime;
    private boolean hasLastContact;

    /**
     * Constructs an {@code Address}.
     *
     * @param dateTime A valid dateTime.
     */
    public LastContact(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), String.format(MESSAGE_CONSTRAINTS, dateTime));

        if (dateTime.isEmpty()) {
            this.hasLastContact = false;
            this.dateTime = null;
            return;
        }
        this.hasLastContact = true;
        this.dateTime = LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }

    /**
     * Returns true if a given string is in valid dateTime format.
     * @param dateTime String to parse into formatter to check whether is valid.
     */
    public static boolean isValidDateTime(String dateTime) {
        if (dateTime.isEmpty()) {
            return true;
        }
        String trimmedDateTime = dateTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDateTime.parse(trimmedDateTime, formatter);
            return true; // Successfully parsed, input matches the format
        } catch (DateTimeParseException e) {
            return false; // Parsing failed, input does not match the format
        }
    }

    @Override
    public String toString() {
        if (!hasLastContact) {
            return "";
        }
        return this.dateTime.format(DATETIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LastContact)) {
            return false;
        }

        LastContact otherDateTime = (LastContact) other;
        return this.dateTime.equals(otherDateTime.dateTime);
    }

    @Override
    public int hashCode() {
        return this.dateTime.hashCode();
    }
}
