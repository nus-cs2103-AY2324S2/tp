package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Set;

import seedu.address.model.person.Attribute;

/**
 * Represents a set of Tags in the address book.
 */
public class TagSet extends Attribute<Set<Tag>> {
    /**
     * Constructs a {@code TagSet}
     *
     * @param tags Set of valid tags
     */
    public TagSet(Set<Tag> tags) {
        super(tags);
        requireNonNull(tags);
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    @Override
    public Set<Tag> getValue() {
        return Collections.unmodifiableSet(super.getValue());
    }

    /**
     * Determine if the tags stored is a match with a tags specified.
     * Returns true if tag set contains any of the tags specified.
     *
     * @param otherValue Other value to check against
     *
     * @return True if specified value is a match, False otherwise
     */
    @Override
    public boolean isMatch(Object otherValue) {
        if (!(otherValue instanceof Set<?>)) {
            return false;
        }

        Set<?> other = ((Set<?>) otherValue);

        if (other.isEmpty()) {
            return true;
        }

        if (!(other.iterator().next() instanceof Tag)) {
            return false;
        }

        // Already checked that set contains Tag objects, so it is safe to cast
        @SuppressWarnings("unchecked")
        Set<Tag> otherTags = (Set<Tag>) other;

        if (otherTags.isEmpty()) {
            return true;
        }

        return otherTags.stream().allMatch(tag -> this.contains(tag));
    }

    /**
     * Determine if the tagset contains the specified tag
     *
     * @param otherValue Tag to check against
     * @return True if tagset contains the specified tag, False otherwise
     */
    public boolean contains(Object otherValue) {
        if (!(otherValue instanceof Tag)) {
            return false;
        }

        Tag other = ((Tag) otherValue);

        return this.getValue().stream().anyMatch(tag -> tag.isMatch(other.getValue()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagSet)) {
            return false;
        }

        TagSet otherTag = (TagSet) other;
        return this.getValue().equals(otherTag.getValue());
    }

    public String toString() {
        return this.getValue().toString();
    }
}
