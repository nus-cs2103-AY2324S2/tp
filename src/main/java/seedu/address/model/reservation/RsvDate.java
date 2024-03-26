package seedu.address.model.reservation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Represents a Reservation's reservation date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRsvDate(String)}
 */
public class RsvDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Reservation date should be valid, and in yyyy-MM-dd format";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd").withResolverStyle(ResolverStyle.STRICT);
    private final LocalDate value;

    /**
     * Constructs a {@code RsvDate}.
     *
     * @param rsvDate A valid reservation date.
     */
    public RsvDate(String rsvDate) {
        requireNonNull(rsvDate);
        checkArgument(isValidRsvDate(rsvDate), MESSAGE_CONSTRAINTS);
        this.value = LocalDate.parse(rsvDate, FORMATTER);
    }

    /**
     * Returns true if a given string is a valid reservation date of format yyyy-MM-dd.
     */
    public static boolean isValidRsvDate(String test) {
        try {
            LocalDate.parse(test, FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        RsvDate rsvDate = (RsvDate) other;
        return value.equals(rsvDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
