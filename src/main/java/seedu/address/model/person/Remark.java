package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a remark for a Person in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {

    public final String value;

    /**
     * Constructs a {@code Remark}.
     * If the given remark is null or empty, an empty string will be used.
     *
     * @param remark A valid remark, or null.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        this.value = remark;
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
        return value.equals(otherRemark.value);
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
