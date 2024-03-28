package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Person's upcoming appointment in the address book.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidUpcoming(String)}
 */
public class Upcoming implements Comparable<Upcoming> {

    public static final String MESSAGE_CONSTRAINTS = "Invalid input/date. Please follow the date format: "
            + "DD-MM-YYYY HHmm and ensure that the date is valid.";
    public static final String MESSAGE_EDIT_EMPTY_STRING_EXCEPTION = "Upcoming can only take DD-MM-YYYY HHmm "
            + "dateTime format, and it should not be blank";
    private static final String DATETIME_FORMAT = "dd-MM-uuuu HHmm";
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);

    private boolean hasUpcoming;
    private LocalDateTime dateTime;

    /**
     * Constructs an {@code Upcoming} object from a valid date and time string in
     * the format "DD-MM-YYYY HH:MM".
     *
     * @param dateTimeStr a string representing the date and time in the format
     *                    "DD-MM-YYYY HH:MM"
     */
    public Upcoming(String dateTimeStr) {
        requireNonNull(dateTimeStr);
        checkArgument(isValidUpcoming(dateTimeStr), MESSAGE_CONSTRAINTS);

        if (dateTimeStr.isEmpty()) {
            this.hasUpcoming = false;
            this.dateTime = null;
            return;
        }
        this.hasUpcoming = true;
        this.dateTime = LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    /**
     * Checks if the given string is a valid date and time combination in the format
     * "DD-MM-YYYY HH:MM".
     *
     * @param dateTimeStr a string representing the date and time in the format
     *                    "DD-MM-YYYY HH:MM"
     * @return true if the string is a valid date and time combination, false
     *         otherwise
     */
    public static boolean isValidUpcoming(String dateTimeStr) {
        if (dateTimeStr.isEmpty()) {
            return true;
        }
        try {
            LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if there are upcoming events or tasks associated with this
     * person.
     *
     * @return true if there are upcoming events or tasks associated with this
     *         person, false otherwise.
     */
    public boolean hasUpcoming() {
        return this.hasUpcoming;
    }

    @Override
    public int compareTo(Upcoming other) {
        return this.dateTime.compareTo(other.dateTime);
    }

    /**
     * Returns a string representation of this {@code Upcoming} object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        if (!hasUpcoming) {
            return "";
        }
        return dateTime.format(DATETIME_FORMATTER);
    }

    /**
     * Checks if this {@code Upcoming} object is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Upcoming other = (Upcoming) obj;

        if (!this.hasUpcoming && !other.hasUpcoming) {
            return true;
        }

        return this.hasUpcoming == other.hasUpcoming && this.dateTime.equals(other.dateTime);
    }

    /**
     * Returns the hash code value for this {@code Upcoming} object.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

}
