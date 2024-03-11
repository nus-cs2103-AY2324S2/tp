
package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public final class Points {
    public static final String MESSAGE_CONSTRAINTS =
            "Points should be a non-negative integer.";

    private final int value;

    /**
     * Constructs a {@code Points} object.
     *
     * @param points A valid points string.
     */
    private Points(final String points) {
        requireNonNull(points);
        checkArgument(isValidPoints(points), MESSAGE_CONSTRAINTS);
        this.value = Integer.parseInt(points);
    }

    /**
     * Returns true if a given string is a valid points amount.
     *
     * @param test The string to test.
     * @return true if the string represents a non-negative integer.
     */
    public static boolean isValidPoints(final String test) {
        try {
            final int value = Integer.parseInt(test);
            return value >= 0; // Points must be non-negative
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
     *
     * @return The integer value of the points.
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Points)) {
            return false;
        }

        Points otherPoints = (Points) other;
        return this.value == otherPoints.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
