package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's location in the internship book.
 */
public class Location {
    public static final String MESSAGE_CONSTRAINTS = "Locations have to be either local, overseas, or remote";

    public static final String VALIDATION_REGEX = "(?i)local|remote|overseas";

    /**
     * Enum of locations
     */
    public enum LocationEnum {
        LOCAL,
        OVERSEAS,
        REMOTE,
        UNKNOWN
    }

    private final LocationEnum location;

    /**
     * Constructs a {@code ApplicationStatus}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        this.location = Location.LocationEnum.valueOf(location.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid ApplicationStatus.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return location.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        return location.equals(otherLocation.location);
    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }

    /**
     * Returns the location of the internship.
     */
    public LocationEnum getLocation() {
        return location;
    }
}
