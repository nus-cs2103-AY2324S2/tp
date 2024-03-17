package seedu.address.model.tag;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag} matches all the tags given.
 */
public class TagsAndFoundPredicate implements Predicate<Person> {

    private final Set<Tag> tags;

    public TagsAndFoundPredicate(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().containsAll(tags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TagsAndFoundPredicate)) {
            return false;
        }

        TagsAndFoundPredicate otherTagsAndFoundPredicate = (TagsAndFoundPredicate) other;
        return tags.equals(otherTagsAndFoundPredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tags", tags).toString();
    }

}
