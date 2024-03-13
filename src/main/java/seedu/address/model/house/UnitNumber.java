package seedu.address.model.house;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a House's unit number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUnitNumber(String)}
 */
public class UnitNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "The unit number should only contain numbers, it should be at least 1 digit "
                    + "and at most 3 digits long, and cannot be '0', '00' or '000'.";
    public static final String VALIDATION_REGEX = "\\d{1,3}";
    public static final String ZERO_REGEX = "^0+$";

    public final String value;

    /**
     * Constructs a {@code UnitNumber}.
     *
     * @param unitNumber A valid unit number.
     */
    public UnitNumber(String unitNumber) {
        requireNonNull(unitNumber);
        checkArgument(isValidUnitNumber(unitNumber), MESSAGE_CONSTRAINTS);
        value = unitNumber;
    }

    /**
     * Returns true if a given string is a valid unit number.
     *
     * @param test The string to test.
     * @return true if the test matches the VALIDATION_REGEX and is not "0".
     */
    public static boolean isValidUnitNumber(String test) {
        return test.matches(VALIDATION_REGEX) && !test.matches(ZERO_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof UnitNumber)) {
            return false;
        }

        UnitNumber otherUnitNumber = (UnitNumber) other;
        return value.equals(otherUnitNumber.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
