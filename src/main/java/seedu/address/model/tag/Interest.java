package seedu.address.model.tag;

/**
 * Represents an interest tag in the address book.
 * Inherits from the {@code Tag} class.
 */
public class Interest extends Tag {

    /**
     * Constructs an interest tag with the specified tag name.
     *
     * @param tagName The name of the interest tag.
     */
    public Interest(String tagName) {
        super(tagName);
    }

    /**
     * Returns the string representation of the interest tag.
     * The format is "{tagName}".
     *
     * @return The string representation of the interest tag.
     */
    public String toString() {
        return '{' + tagName + '}';
    }
}
