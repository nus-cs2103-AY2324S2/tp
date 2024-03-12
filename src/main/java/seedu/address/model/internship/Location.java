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
        REMOTE,
        UNKNOWN
    }

    public final LocationEnum location;

    /**
     * Constructs a {@code ApplicationStatus}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        if (location == null) {
            this.location = LocationEnum.UNKNOWN;
            return;
        }
        switch (location) {
        case "local":
            this.location = LocationEnum.LOCAL;
            break;
        case "overseas":
            this.location = LocationEnum.OVERSEAS;
            break;
        case "remote":
            this.location = LocationEnum.REMOTE;
            break;
        default:
            throw new IllegalArgumentException("Invalid location: " + location);
        }
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
        case UNKNOWN:
            return "Unknown";
        default:
            throw new IllegalArgumentException("Unexpected location: " + location);
        }
    }



    @Override
    public int hashCode() {
        return location.hashCode();
    }

}
