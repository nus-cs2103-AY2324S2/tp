package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the Tag given.
 */
public class TagMatchesPredicate implements Predicate<Person> {
    private final Tag tag;

    public TagMatchesPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().contains(tag);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagMatchesPredicate)) {
            return false;
        }

        TagMatchesPredicate otherTag = (TagMatchesPredicate) other;
        return tag.equals(otherTag.tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tag", tag).toString();
    }
}
