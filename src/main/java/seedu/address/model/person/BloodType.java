package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;


/**
 * Represents a Person's blood type in the address book.
 * Guarantees: immutable;
 */
public class BloodType {
    public static final String MESSAGE_CONSTRAINTS =
            "BloodType should be A, B, AB, O. Rh should be either + or -";
    private enum Type { A, B, AB, O };
    private enum Rh { POSITIVE, NEGATIVE };
    private final Type type;
    private final Rh rh;

    /**
     * Constructs a {@code BloodType}.
     *
     * @param type A valid blood type.
     * @param rh A valid Rh factor.
     */
    public BloodType(String type, String rh) {
        requireNonNull(type, rh);
        checkArgument(isValidBloodType(type, rh), MESSAGE_CONSTRAINTS);
        this.type = Type.valueOf(type);
        this.rh = rh == "+" ? Rh.POSITIVE : Rh.NEGATIVE;
    }

    /**
     * A method for testing if two strings form a valid BloodType
     * @param testType String for storing testing type
     * @param testRh String for storing testing rh
     * @return Boolean whether test passes or fails
     */
    public static boolean isValidBloodType(String testType, String testRh) {
        try {
            Type type = Type.valueOf(testType);
            if (testRh == "+" || testRh == "-") {
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    public String getType() {
        return type.toString();
    }

    public String getRh() {
        return rh.toString();
    }

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return type == null ? alt : getType() + getRh();
    }

    @Override
    public String toString() {
        return this.type.toString() + this.rh.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, rh);
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
        return type.equals(otherBloodType.type) && rh.equals(otherBloodType.rh);
    }
}
