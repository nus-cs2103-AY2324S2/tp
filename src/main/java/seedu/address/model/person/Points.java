package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Points {
    public static final String MESSAGE_CONSTRAINTS = "Points should be a non-negative integer.";

    public final int value;

    public Points(String points) {
        requireNonNull(points);
        checkArgument(isValidPoints(points), MESSAGE_CONSTRAINTS);
        this.value = Integer.parseInt(points);;
    }

    /**
     * Returns true if a given string is a valid points amount.
     */
    public static boolean isValidPoints(String test) {
        try {
            int value = Integer.parseInt(test);
            return value >= 0; // Example constraint: points must be non-negative.
        } catch (NumberFormatException e) {
            return false; // The string was not an integer.
        }
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    /**
     * Returns the integer value of points.
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Points)) {
            return false;
        }

        Points otherPoints = (Points) other;
        return this.value == otherPoints.value; // Compare integer values directly.
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}