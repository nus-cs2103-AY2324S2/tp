package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Data structure to house Class related information
 */
public class FormClass {

    public static final String MESSAGE_CONSTRAINTS = "Class names should be alphanumeric and at most 2 words long.";
    public static final String VALIDATION_REGEX = "^\\p{Alnum}+(?:\\s\\p{Alnum}+)?$";

    public final String value;

    /**
     * Constructs a {@code Class}.
     *
     * @param className A valid class name.
     */
    public FormClass(String className) {
        requireNonNull(className);
        checkArgument(isValidClassName(className), MESSAGE_CONSTRAINTS);
        this.value = className;
    }

    /**
     * Returns true if a given string is a valid class name.
     */
    public static boolean isValidClassName(String test) {
        return (test.matches(VALIDATION_REGEX) && !(test.trim() == ""));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FormClass)) {
            return false;
        }

        FormClass otherFormClass = (FormClass) other;
        return value.equals(otherFormClass.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return value;
    }

}

