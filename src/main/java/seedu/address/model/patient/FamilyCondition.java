package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's family condition in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFamilyCondition(String)}
 */
public class FamilyCondition {


    public static final String MESSAGE_CONSTRAINTS =
            "Family condition can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String familyCondition;

    /**
     * Constructs a {@code FamilyCondition}.
     *
     * @param condition A valid family condition.
     */
    public FamilyCondition(String condition) {
        requireNonNull(condition);
        checkArgument(isValidFamilyCondition(condition), MESSAGE_CONSTRAINTS);
        familyCondition = condition;
    }

    /**
     * Returns true if a given string is a valid family condition.
     */
    public static boolean isValidFamilyCondition(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return familyCondition;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FamilyCondition)) {
            return false;
        }

        FamilyCondition otherFamilyCondition = (FamilyCondition) other;
        return familyCondition.equals(otherFamilyCondition.familyCondition);
    }

    @Override
    public int hashCode() {
        return familyCondition.hashCode();
    }

}
