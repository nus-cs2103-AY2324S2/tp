package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;

    private TagStatus tagStatus;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName, TagStatus tagStatus) {
        requireNonNull(tagName);
        // require the tagStatus not to be null for now
        // in the future, a null tagStatus input should be set to INCOMPLETE_GOOD
        // by default
        requireNonNull(tagStatus);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        this.tagStatus = tagStatus;
    }

    public TagStatus getTagStatus() {
        return tagStatus;
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

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[ " + tagName + " : " + tagStatus + " ]";
    }

    public static void isTagNameValid(String tagName) throws IllegalArgumentException {
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * @param currTags current tag set to be updated.
     * @param tagName name of the new tag.
     * @param tagStatus tagStatus of the new tag.
     * @return
     */
    public static Set<Tag> updateTagsWithNewTag(Set<Tag> currTags, String tagName, TagStatus tagStatus) {
        // Instead of retrieving the Tag sharing the same name and update it,
        // remove the potentially existing Tag of the same name from the hashset
        // and then add in a new Tag with the same tagName but updated tagStatus.
        // This is to avoid having linearly check through the hashset to retrieve
        // the existing Tag
        Tag newTag = new Tag(tagName, tagStatus);
        currTags.remove(newTag);
        currTags.add(new Tag(tagName, tagStatus));
        return currTags;
    }

}
