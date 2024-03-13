package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's stars in the address book.
 */
public class Star {

    public static final String MESSAGE_CONSTRAINTS =
            "Stars given should be more than 0.";

    public static final Star NOSTAR = new Star(0);

    public final Integer noOfStars; // number of stars given to a student

    /**
     * Constructs a {@code Star}.
     *
     * @param noOfStars A valid number.
     */
    public Star(Integer noOfStars) {
        requireNonNull(noOfStars);
        checkArgument(isValidStar(noOfStars), MESSAGE_CONSTRAINTS);
        this.noOfStars = noOfStars;
    }

    /**
     * Returns true if a given string is a valid number.
     */
    public static boolean isValidStar(Integer noOfStars) {
        return noOfStars > 0;
    }

    @Override
    public String toString() {
        return this.noOfStars.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Star)) {
            return false;
        }

        Star otherPhone = (Star) other;
        return noOfStars.equals(otherPhone.noOfStars);
    }

    @Override
    public int hashCode() {
        return noOfStars.hashCode();
    }
}