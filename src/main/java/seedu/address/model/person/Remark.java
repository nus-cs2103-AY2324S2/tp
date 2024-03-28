package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark Any remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Executes the given function if the value is present.
     *
     * @param function the function to be executed if the value is present
     */
    public void ifPresent(Function<String, StringBuilder> function) {
        if (value != null) {
            function.apply(value);
        }
    }

    @Override
    public String toString() {
        return value;
    }

    /**
        * Returns true if this remark is equal to the specified object.
        * Two remarks are considered equal if they have the same value.
        *
        * @param other the object to compare this remark with
        * @return true if the specified object is equal to this remark, false otherwise
        */
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
