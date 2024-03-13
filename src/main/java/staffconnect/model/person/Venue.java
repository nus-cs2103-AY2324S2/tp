package staffconnect.model.person;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's venue in the staff book.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidVenue(String)}
 */
public class Venue {

    public static final String MESSAGE_CONSTRAINTS = "Venues can take any values, and it should not be blank";

    /*
     * The first character of the venue must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Venue}.
     *
     * @param venue A valid venue.
     */
    public Venue(String venue) {
        requireNonNull(venue);
        checkArgument(isValidVenue(venue), MESSAGE_CONSTRAINTS);
        value = venue;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidVenue(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Venue)) {
            return false;
        }

        Venue otherVenue = (Venue) other;
        return value.equals(otherVenue.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
