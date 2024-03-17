package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * Represents a list of unique tags.
 * A UniqueTagList ensures that no duplicate tags are stored.
 */
public class UniqueTagList implements Iterable<Tag> {

    private final ObservableSet<Tag> internalSet = FXCollections.observableSet();

    /**
     * Constructs an empty UniqueTagList.
     */
    public UniqueTagList() {}

    /**
     * Constructs a UniqueTagList with the given tags.
     * @param tags A list of tags.
     */
    public UniqueTagList(Set<Tag> tags) {
        internalSet.addAll(tags);
    }

    /**
     * Returns true if the list contains an equivalent tag as the given argument.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalSet.stream().anyMatch(toCheck::isSameTag);
    }

    /**
     * Adds a tag to the list.
     * The tag must not already exist in the list.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTagException();
        }
        internalSet.add(toAdd);
    }

    /**
     * Replaces the specified tag in the list with the new tag.
     * @param oldTag The tag to be replaced.
     * @param newTag The new tag to replace the old tag.
     */
    public void replace(Tag oldTag, Tag newTag) {
        requireAllNonNull(oldTag, newTag);

        if (!internalSet.contains(oldTag)) {
            throw new PersonNotFoundException();
        }

        if (!oldTag.isSameTag(newTag) && contains(newTag)) {
            throw new DuplicateTagException();
        }

        internalSet.remove(oldTag);
        internalSet.add(newTag);
    }

    /**
     * Removes the equivalent tag from the list.
     * The tag must exist in the list.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);
        if (!internalSet.remove(toRemove)) {
            throw new TagNotFoundException();
        }
    }

    /**
     * Replaces all tags in this list with the tags from the replacement list.
     * @param replacement The replacement UniqueTagList.
     */
    public void setTags(UniqueTagList replacement) {
        requireNonNull(replacement);
        internalSet.clear();
        internalSet.addAll(replacement.internalSet);
    }

    /**
     * Replaces the tags in the list with the given set of tags.
     * @param tags The set of tags to replace the current tags.
     */
    public void setTags(Set<Tag> tags) {
        requireAllNonNull(tags);
        internalSet.clear();
        internalSet.addAll(tags);
    }

    /**
     * Returns the backing set as an unmodifiable {@code ObservableSet}.
     */
    public ObservableSet<Tag> asUnmodifiableObservableSet() {
        return FXCollections.unmodifiableObservableSet(internalSet);
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalSet.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // handle nulls
        if (!(other instanceof UniqueTagList)) {
            return false;
        }

        UniqueTagList otherUniqueTagSet = (UniqueTagList) other;
        return internalSet.equals(otherUniqueTagSet.internalSet);
    }

    @Override
    public String toString() {
        return internalSet.toString();
    }

    /**
     * Returns true if {@code tags} contains only unique tags.
     */
    private boolean tagsAreUnique(Set<Tag> tags) {
        Set<Tag> uniqueTags = new HashSet<>();
        for (Tag tag : tags) {
            if (!uniqueTags.add(tag)) {
                return false; // Duplicate tag found
            }
        }
        return true; // No duplicate tags found
    }

}
