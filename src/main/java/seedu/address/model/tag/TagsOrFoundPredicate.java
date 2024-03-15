package seedu.address.model.tag;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the tags given.
 */
public class TagsOrFoundPredicate implements Predicate<Person> {

    private final Set<Tag> tags;

    public TagsOrFoundPredicate(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        return tags.stream()
                .anyMatch(tag -> person.getTags().contains(tag));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TagsOrFoundPredicate)) {
            return false;
        }

        TagsOrFoundPredicate otherTagOrFoundPredicate = (TagsOrFoundPredicate) other;
        return tags.equals(otherTagOrFoundPredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tags", tags).toString();
    }

}
