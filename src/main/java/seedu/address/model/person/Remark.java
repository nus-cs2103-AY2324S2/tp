package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a remark for a Person in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}
 */
public class Remark {
    public static final String MESSAGE_CONSTRAINTS = "Remark should be alphanumeric or empty";

    public final String value;

    /**
     * Constructs a {@code Remark}.
     * If the given remark is null or empty, an empty string will be used.
     *
     * @param remark A valid remark, or null.
     */
    public Remark(String remark) {
        requireNonNull(remark);
            checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
            this.value = remark;
    }
    
    /**
     * Returns true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        return true;
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
        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return this.value.equals(otherRemark.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
