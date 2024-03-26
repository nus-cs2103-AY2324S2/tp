package seedu.address.model.tag;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Free Time Tag in Dormie.
 */
public class FreeTimeTag extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Free Time Tag should be Mon-Sun:HHmm-HHmm (24hr format)";
    public static final String VALIDATION_REGEX =
            "^(Mon|Tue|Wed|Thu|Fri|Sat|Sun):(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])-(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])$";

    /**
     * Constructs a {@code FreeTimeTag}.
     *
     * @param tagName A valid tag name.
     */
    public FreeTimeTag(String tagName) {
        super(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
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
