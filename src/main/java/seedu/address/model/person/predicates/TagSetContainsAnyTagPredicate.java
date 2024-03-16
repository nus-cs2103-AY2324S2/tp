package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person.PersonAttribute;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code TagSet} contains any tags in a given
 * TagSet.
 */
public class TagSetContainsAnyTagPredicate extends SearchPredicate<Set<Tag>> {
    public TagSetContainsAnyTagPredicate(Set<Tag> tags) {
        super(tags, PersonAttribute.TAGS);
        requireNonNull(tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tagset", this.getSearchValue()).toString();
    }
}
