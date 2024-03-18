package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Person's status in the address book.
 * Guarantees: immutable;
 */
public class Sex {
    public static final String MESSAGE_CONSTRAINTS = "Sex should be either F or M.";

    /**
     * Represents sex of a person.
     */
    private enum SexType { F, M }
    private final SexType sex;

    /**
     * Constructs a Sex instance.
     */
    public Sex(String sex) {
        requireNonNull(sex);
        checkArgument(isValidSex(sex), MESSAGE_CONSTRAINTS);
        this.sex = SexType.valueOf(sex);
    }

    /**
     * Checks if a String matches the Enum
     * @param testString String of input
     * @return Boolean
     */
    public static boolean isValidSex(String testString) {
        try {
            SexType sexType = SexType.valueOf(testString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.sex.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Sex)) {
            return false;
        }

        Sex otherSex = (Sex) other;
        return sex.equals(otherSex.sex);
    }

    @Override
    public int hashCode() {
        return sex.hashCode();
    }
}
