package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Person's blood type in the address book.
 * Guarantees: immutable;
 */
public class BloodType {
    public static final String MESSAGE_CONSTRAINTS = "BloodType should be either A+, A-, B+, B-, AB+, AB-, O+, O-";
    public static final String VALIDATION_REGEX = "^(A\\+|A-|B\\+|B-|AB\\+|AB-|O\\+|O-)$";

    private final String bloodType;

    /**
     * Constructs a {@code BloodType}.
     *
     * @param bloodType A valid blood type in String format
     */
    public BloodType(String bloodType) {
        requireNonNull(bloodType);
        checkArgument(isValidBloodType(bloodType), MESSAGE_CONSTRAINTS);
        this.bloodType = bloodType;
    }

    /**
     * A method for testing if string forms a valid blood group
     *
     * @param test String for storing testing type
     * @return Boolean whether test passes or fails
     */
    public static boolean isValidBloodType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns given placeholder string if value field is not initialised
     *
     * @param alt An alternate return value
     * @return placeholder string
     */
    public String orElse(String alt) {
        return this.bloodType == null ? alt : this.toString();
    }

    @Override
    public String toString() {
        return this.bloodType;
    }

    @Override
    public int hashCode() {
        return this.bloodType.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof BloodType)) {
            return false;
        }
        BloodType otherBloodType = (BloodType) other;
        return this.bloodType.equals(otherBloodType.bloodType);
    }
}
