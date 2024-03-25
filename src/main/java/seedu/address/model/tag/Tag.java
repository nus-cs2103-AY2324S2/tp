package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
/**
 *  Represents a Tag in Dormie.
 */
public abstract class Tag {
    public static final String MESSAGE_CONSTRAINTS = "Tag is in the wrong format";
    public final String tagName;

    /**
     * Constructs a Tag.
     *
     * @param tagName the name of the tag
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        this.tagName = tagName;
    }

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    /**
     * Format state of Tag object for viewing.
     */
    public abstract String toString();
}
