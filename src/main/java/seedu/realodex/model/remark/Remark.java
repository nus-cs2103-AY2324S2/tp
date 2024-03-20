package seedu.realodex.model.remark;

import static java.util.Objects.requireNonNull;

/**
 * Represents a remark in realodex.
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remarks should be non-empty";

    public final String remarkName;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remarkName A valid remark.
     */
    public Remark(String remarkName) {
        requireNonNull(remarkName);
        this.remarkName = remarkName;
    }

    /**
     * Returns true if a given string is a valid name.
     * However, as of v1.2 there is no concrete check; hence it will always return true.
     * @param test
     * @return
     */
    public static boolean isValidRemark(String test) {
        requireNonNull(test);
        return true;
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
        return remarkName.equals(otherRemark.remarkName);
    }

    @Override
    public int hashCode() {
        return remarkName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return remarkName;
    }

    /**
     * Format state as text for representation.
     */
    public String toStringWithRepresentation() {
        if (remarkName.isBlank()) {
            return "No remark.";
        } else {
            return "Remark: " + remarkName;
        }
    }

}
