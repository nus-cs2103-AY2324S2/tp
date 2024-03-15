package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's stars in the address book.
 */
public class Star {

    public static final String MESSAGE_CONSTRAINTS =
            "Stars given should be more than 0.";

    public static final Star NO_STAR = new Star(0);

    public final Integer numOfStars; // number of stars given to a student

    /**
     * Constructs a {@code Star}.
     *
     * @param numOfStars A valid number.
     */
    public Star(Integer numOfStars) {
        requireNonNull(numOfStars);
        checkArgument(isValidStar(numOfStars), MESSAGE_CONSTRAINTS);
        this.numOfStars = numOfStars;
    }

    /**
     * Returns true if a given string is a valid number.
     */
    public static boolean isValidStar(Integer noOfStars) {
        return noOfStars >= 0;
    }

    @Override
    public String toString() {
        return this.numOfStars.toString();
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
        return numOfStars.equals(otherPhone.numOfStars);
    }

    @Override
    public int hashCode() {
        return numOfStars.hashCode();
    }
}
