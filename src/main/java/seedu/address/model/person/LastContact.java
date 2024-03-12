package seedu.address.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a client's last contacted date and time in the address book.
 */
public class LastContact {

    public static final String MESSAGE_CONSTRAINTS = "Expected DATETIME format: DD-MM-YYYY HHmm\n" +
                                                    "Actual format: %s";
    public String dateTime;

    /**
     * Constructs an {@code Address}.
     *
     * @param dateTime A valid dateTime.
     */
    public LastContact(String dateTime) {
        requireNonNull(dateTime);
        if (!dateTime.equals("-")) {
            checkArgument(isValidDateTime(dateTime), String.format(MESSAGE_CONSTRAINTS, dateTime));
        }
        this.dateTime = dateTime;
    }

    /**
     * Returns true if a given string is in valid dateTime format.
     * @param dateTime String to parse into formatter to check whether is valid.
     */
    public boolean isValidDateTime(String dateTime) {
        String trimmedDateTime = dateTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        try {
            LocalDateTime.parse(trimmedDateTime, formatter);
            return true; // Successfully parsed, input matches the format
        } catch (DateTimeParseException e) {
            return false; // Parsing failed, input does not match the format
        }
    }

    public String getDateTimeString() {
        return this.dateTime;
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
