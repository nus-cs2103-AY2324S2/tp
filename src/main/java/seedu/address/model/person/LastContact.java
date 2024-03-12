package seedu.address.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a client's last contacted date and time in the dateTime book.
 */
public class LastContact {

    public static final String MESSAGE_CONSTRAINTS = "Datetime should only take format DD-MM-YYYY HHmm";
    public LocalDateTime dateTime;

    /**
     * Constructs an {@code Address}.
     *
     * @param dateTime A valid dateTime.
     */
    public LastContact(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid dateTime.
     * For example, 25-09-2025 0600.
     */
    public boolean isValidDateTime(String dateTime) {
        String trimmedDateTime = dateTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        try {
            this.dateTime = LocalDateTime.parse(trimmedDateTime, formatter);
            return true; // Successfully parsed, input matches the format
        } catch (DateTimeParseException e) {
            return false; // Parsing failed, input does not match the format
        }
    }

    public String getDateTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return this.dateTime.format(formatter);
    }

    // Will be used for sorting
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
