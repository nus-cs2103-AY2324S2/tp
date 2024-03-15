package seedu.address.model.person;

/**
 * Defines an attribute in a Person in the address book.
 */
public abstract class Attribute<T extends Object> {
    private final T value;

    protected Attribute(T value) {
        this.value = value;
    }

    /**
     * Get the value stored in this attribute.
     *
     * @return Value stored in this attribute.
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Determine if a specified value is a match with the value stored in this
     * attriute.
     *
     * @param otherValue Value to check against
     *
     * @return True if specified value is a match, False otherwise
     */
    public abstract boolean isMatch(T otherValue);
}
