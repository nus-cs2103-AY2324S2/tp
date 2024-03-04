package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public final String value;
    /**
     * Constructs a {@code Remark} object.
     *
     * @param remark A string representing the remark about a person. Cannot be null.
     * @throws NullPointerException if the input remark is null.
     */

    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }
    /**
     * Returns the string representation of the remark.
     *
     * @return A string containing the value of the remark.
     */

    @Override
    public String toString() {
        return value;
    }
    /**
     * Compares this Remark object to another object to determine if they are equal.
     * Two Remark objects are considered equal if they have the same value.
     *
     * @param other The object to be compared with this Remark object for equality.
     * @return true if the other object is an instance of Remark and has the same value as this Remark object;
     *         false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }
    /**
     * Returns a hash code value for the remark.
     * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return A hash code value for this remark.
     */

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
