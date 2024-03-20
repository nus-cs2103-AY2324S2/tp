package vitalconnect.model.person.medicalinformation;

import static java.util.Objects.requireNonNull;
import static vitalconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the clinic.
 * Guarantees: immutable; is valid as declared in {@link #isValidHeight(String)}
 */
public class Height {

    public static final String MESSAGE_CONSTRAINTS =
            "Height should only contain alphanumerical measured in cm, and should be bigger than 0";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9]*\\.?[0-9]+";

    public final String value;
    /**
     * Constructs a {@code Height}.
     *
     * @param height A valid height.
     */
    public Height(String height) {
        requireNonNull(height);
        checkArgument(isValidHeight(height), MESSAGE_CONSTRAINTS);
        this.value = height;
    }

    /**
     * Constructs a {@code Height}.
     */
    public Height() {
        this.value = "";
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidHeight(String test) {
        if (test.isEmpty()) {
            return true;
        }
        return test.matches(VALIDATION_REGEX) && Float.parseFloat(test) > 0;
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
        if (!(other instanceof Height)) {
            return false;
        }

        Height otherHeight = (Height) other;
        return value.equals(otherHeight.value);
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
