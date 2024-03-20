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
     * @param remark The remark to be associated with the person.
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
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                        && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
