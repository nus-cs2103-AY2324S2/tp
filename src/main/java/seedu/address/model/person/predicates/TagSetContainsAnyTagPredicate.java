package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person.PersonAttribute;
import seedu.address.model.tag.TagSet;

/**
 * Tests that a {@code Person}'s {@code TagSet} contains any tags in a given
 * TagSet.
 */
public class TagSetContainsAnyTagPredicate extends SearchPredicate<TagSet> {
    public TagSetContainsAnyTagPredicate(TagSet tags) {
        super(tags, PersonAttribute.TAGS);
        requireNonNull(tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tagset", this.getSearchValue()).toString();
    }
}
