package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.tag.Tag;

/**
 * Represents an abstraction for a list of tags.
 */
public class Tags implements Field {

    public static final Prefix PREFIX_TAG = new Prefix("t/");
    private final Set<Tag> tags;

    /**
     * Constructs a new Tags from a list of tags.
     * @param tags A list of tags.
     */
    public Tags(Tag... tags) {
        requireNonNull(tags);
        this.tags = Stream.of(tags)
                          .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Constructs a new Tags from a list of strings.
     * @param tagNames A list of strings.
     */
    public Tags(String... tagNames) {
        requireNonNull(tagNames);
        this.tags = Stream.of(tagNames)
                          .map(Tag::new)
                          .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Tags}.
     */
    public static Tags of(Collection<String> tags) throws IllegalArgumentException {
        requireNonNull(tags);
        return new Tags(tags.toArray(new String[0]));
    }

    @JsonValue
    private Set<Tag> get() {
        return tags;
    }

    public Stream<Tag> stream() {
        return tags.stream();
    }

    @Override
    public String toString() {
        return tags.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tags)) {
            return false;
        }

        Tags otherTags = (Tags) other;
        return tags.equals(otherTags.tags);
    }

    @Override
    public int hashCode() {
        return tags.hashCode();
    }

}
