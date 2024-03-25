package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

enum Tags {
    NR,
    OA,
    I,
    R,
    O;

    // Method to check if a given string is a valid enum value
    public static boolean isValidTag(String input) {
        for (Tags tag : Tags.values()) {
            if (tag.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Represents an internship application's progress
 * Stages of Progress: No Reply (NR), Online Assesment (OA), Interview (I), Reject (R), Offer (O)
 */
public class Tag {
    public static final String MESSAGE_CONSTRAINTS = "Tags should only contain NR, OA, I, R and O";
    public final String value;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tag A valid Tag.
     */
    public Tag(String tag) {
        requireNonNull(tag);
        checkArgument(Tags.isValidTag(tag), MESSAGE_CONSTRAINTS);
        value = tag;
    }

    @Override
    public String toString() {
        return '[' + value + ']';
    }

    /*
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references
     * </p>
     *
     * @param other the reference object with which to compare
     * @return boolean true or false
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return value.equals(otherTag.value);
    }

    /**
     * Returns if a given string / tag is a valid tag.
     */
    public static boolean isValidTag(String input) {
        return Tags.isValidTag(input);
    }

    /*
     * Function overloading - isValidTag(String) & isValidTag(Tag)
     */
    public static boolean isValidTag(Tag tag) {
        return Tags.isValidTag(tag.value);
    }

    public int hashCode() {
        return value.hashCode();
    }
}
