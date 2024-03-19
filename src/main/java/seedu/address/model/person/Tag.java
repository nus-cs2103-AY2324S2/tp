package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's tag in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTag(String)}
 */
public class Tag {

    /**
     * Represents the type of tag.
     */
    public enum TagType {
        Professor, TA, Student, None;

        /**
         * Returns the string representation of the tag type.
         * @param tagType
         * @return
         */
        public static String toString(TagType tagType) {
            switch (tagType) {
            case Professor:
                return "Professor";
            case TA:
                return "TA";
            case Student:
                return "Student";
            default:
                return "None";
            }
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "Tag can only these values: Professor, TA, Student, None";

    public final TagType value;

    /**
     * Constructs an {@code Tag}.
     *
     * @param tag A valid tag.
     */
    public Tag(String tag) {
        requireNonNull(tag);
        checkArgument(isValidTag(tag), MESSAGE_CONSTRAINTS);
        value = parseTag(tag);
    }

    /**
     * Parses a tag and returns the corresponding TagType.
     * @param tag
     * @return
     */
    public static TagType parseTag(String tag) {
        switch (tag) {
        case "Professor":
            return TagType.Professor;
        case "TA":
            return TagType.TA;
        case "Student":
            return TagType.Student;
        default:
            return TagType.None;
        }
    }

    /**
     * Returns true if a given string is a valid tag.
     */
    public static boolean isValidTag(String test) {
        switch (test) {
        case "Professor":
        case "TA":
        case "Student":
        case "None":
            return true;
        default:
            return false;
        }
    }

    @Override
    public String toString() {
        switch (value) {
        case Professor:
            return "Professor";
        case TA:
            return "TA";
        case Student:
            return "Student";
        default:
            return "None";
        }
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
        return value.equals(otherTag.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
