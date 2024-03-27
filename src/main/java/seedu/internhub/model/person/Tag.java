package seedu.internhub.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.internhub.commons.util.AppUtil.checkArgument;

enum Tags {
    NR("No Reply"),
    OA("Online Assessment"),
    I("Interview"),
    R("Reject"),
    O("Offer");


    public final String descriptiveName;

    Tags(String descriptiveName) {
        this.descriptiveName = descriptiveName;
    }

    // Method to check if a given string is a valid enum value
    public static boolean isValidTag(String input) {
        for (Tags tag : Tags.values()) {
            if (tag.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    // Method to find a tag by string
    public static Tags fromString(String input) {
        for (Tags tag : Tags.values()) {
            if (tag.name().equalsIgnoreCase(input) || tag.descriptiveName.equalsIgnoreCase(input)) {
                return tag;
            }
        }
        throw new IllegalArgumentException("Invalid tag: " + input);
    }
}

/**
 * Represents an internship application's progress
 * Stages of Progress: No Reply (NR), Online Assesment (OA), Interview (I), Reject (R), Offer (O)
 */
public class Tag {
    public static final String MESSAGE_CONSTRAINTS = "Tags should only contain NR, OA, I, R and O";
    public final Tags value;
    public final String tagInput;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tag A valid Tag.
     */
    public Tag(String tag) {
        requireNonNull(tag);
        this.tagInput = tag;
        Tags enumTag = Tags.fromString(tag);
        checkArgument(Tags.isValidTag(tag), MESSAGE_CONSTRAINTS);
        this.value = enumTag;
    }

    @Override
    public String toString() {
        return value.descriptiveName;
    }

    /*
     * Indicates whether some other object is "equal to" this one.
     *
     * @param other the reference object with which to compare
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tag)) {
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
        return Tags.isValidTag(tag.getTagName());
    }

    public String getTagName() {
        return this.value.descriptiveName;
    }

    public String getTagShort() {
        return this.tagInput;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
