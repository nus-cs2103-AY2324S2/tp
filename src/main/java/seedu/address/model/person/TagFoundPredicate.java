package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the tag given.
 */
public class TagFoundPredicate implements Predicate<Person> {

    private final String tag;

    public TagFoundPredicate(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        Tag tagToBeFound = new Tag(tag);
        return person.getTags().contains(tagToBeFound);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagFoundPredicate)) {
            return false;
        }

        TagFoundPredicate otherTagFoundPredicate = (TagFoundPredicate) other;
        return tag.equals(otherTagFoundPredicate.tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tag", tag).toString();
    }

}
