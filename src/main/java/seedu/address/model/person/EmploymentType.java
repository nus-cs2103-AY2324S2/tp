package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's employment type in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmploymentType(String)}
 */
public class EmploymentType {

    public static final String MESSAGE_CONSTRAINTS =
            "Employment type should only be either 'pt' or 'ft'.";
    public static final String VALIDATION_REGEX = "^(pt|ft)$";
    public final String value;


    public EmploymentType(String employmentType) {
        requireNonNull(employmentType);
        checkArgument(isValidEmploymentType(employmentType), MESSAGE_CONSTRAINTS);
        value = employmentType;
    }

    /**
     * Returns true if a given string is a valid employment type.
     */
    public static boolean isValidEmploymentType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmploymentType)) {
            return false;
        }

        EmploymentType otherEmploymentType = (EmploymentType) other;
        return value.equals(otherEmploymentType.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
