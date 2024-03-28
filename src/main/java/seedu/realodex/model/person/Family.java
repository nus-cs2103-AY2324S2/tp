package seedu.realodex.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.realodex.commons.util.AppUtil.checkArgument;

/**
 * Represents the family size of a person in realodex.
 * Guarantees: family size is present and not null, and adheres to specific constraints.
 */
public class Family {

    /** Message for constraints on family size. */
    public static final String MESSAGE_CONSTRAINTS = "Family size should be at least 1";
    public static final String VALIDATION_REGEX = "^[1-9]\\d*$";

    /** The family size. */
    private String familySize;


    /**
     * Constructs a {@code Family} instance with the given family size including him or herself.
     *
     * @param familySize The size of the family.
     */
    public Family(String familySize) {
        requireNonNull(familySize);
        checkArgument(isValidFamily(familySize), MESSAGE_CONSTRAINTS);
        this.familySize = familySize;
    }

    public Family() {
        this.familySize = "1";
    }

    /**
     * Checks if the given family size is a valid value.
     *
     * @param familySize The family size to check.
     * @return True if the family size is greater than or equal to zero, false otherwise.
     */
    public static boolean isValidFamily(String familySize) {
        return familySize.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation of the family size.
     *
     * @return The string representation of the family size.
     */
    @Override
    public String toString() {
        return familySize.toString();
    }

    /**
     * Returns a string representation of the family size with additional descriptive text.
     *
     * @return A string representation with descriptive text.
     */
    public String toStringWithRepresentation() {
        return "Family size is " + familySize;
    }

    /**
     * Checks if this {@code Family} instance is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }
        if (!(other instanceof Family)) {
            return false; // instanceof handles nulls
        }
        Family otherFamily = (Family) other;
        return familySize.equals(otherFamily.familySize); // state check
    }
}
