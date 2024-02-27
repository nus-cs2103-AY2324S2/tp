package seedu.address.model.person.fields.optional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.tag.Tag;

/**
 * Represents an abstraction for a list of tags.
 */
public class Tags implements OptionalField {

    private final Set<Tag> tags;

    /**
     * Constructs a new Tags from a list of tags.
     * @param tags A list of tags, or null.
     */
    public Tags(Tag... tags) {
        this.tags = Stream.of(tags)
                          .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Constructs a new Tags from a list of strings.
     * @param tagNames A list of strings, or null.
     */
    public Tags(String... tagNames) {
        for (String name : tagNames) {
            if (name.isEmpty()) System.out.println(tagNames.length);
        }
        this.tags = Stream.of(tagNames)
                          .map(Tag::new)
                          .collect(Collectors.toUnmodifiableSet());
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
