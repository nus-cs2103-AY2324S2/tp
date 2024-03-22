package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final int MAX_TAG_LENGTH = 50;
    public static final String MESSAGE_LENGTH_CONSTRAINTS = "Tag names must be less than "
            + MAX_TAG_LENGTH + " characters";
    public static final String MESSAGE_CONSTRAINTS = "Tag names must not be empty, less than" + MAX_TAG_LENGTH
            + " characters and can only contain alphanumeric characters or spaces";
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} ]*$";
    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) throws IllegalArgumentException {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        checkArgument(tagName.length() <= MAX_TAG_LENGTH, MESSAGE_LENGTH_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is of valid tag length.
     */
    public static boolean isValidTagLength(String test) {
        return test.length() <= MAX_TAG_LENGTH;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return tagName.equals(otherTag.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
