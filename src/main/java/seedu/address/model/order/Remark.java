package seedu.address.model.order;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the remark of an order in the order book.
 */
public class Remark {
    public static final String MESSAGE_CONSTRAINTS = "Remark can take any values, and it should not be blank";

    /**
     * The first character of the remark must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        this.value = remark;
    }

    /**
     * Returns true if a given string is a valid remark.
     *
     * @param test the remark to be tested.
     * @return true if the remark is valid.
     */
    public static boolean isValidRemark(String test) {
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

        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return otherRemark.value.equals(this.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
