package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Internship's location in the internship book.
 */
public class Location {
    /**
     * Enum of locations
     */
    public enum LocationEnum {
        LOCAL,
        OVERSEAS,
        REMOTE
    }

    public final LocationEnum location;

    /**
     * Constructs a {@code ApplicationStatus}.
     *
     * @param location A valid location.
     */
    public Location(LocationEnum location) {
        requireNonNull(location);
        this.location = location;
    }

    @Override
    public String toString() {
        switch (location) {
        case LOCAL:
            return "Local";
        case OVERSEAS:
            return "Overseas";
        case REMOTE:
            return "Remote";
        default:
            throw new IllegalArgumentException("Unexpected location: " + location);
        }
    }



    @Override
    public int hashCode() {
        return location.hashCode();
    }

}
