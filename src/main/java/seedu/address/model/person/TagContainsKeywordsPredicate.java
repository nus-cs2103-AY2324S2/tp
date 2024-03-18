package seedu.address.model.person;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> tags;

    public TagContainsKeywordsPredicate(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream() // Stream over the tags of the person
                .anyMatch(personTag -> tags.stream() // Stream over the given tags to check
                        .anyMatch(tagKeyword -> StringUtil.containsWordIgnoreCase(personTag.getTagName(), tagKeyword))); // Check if any tag matches any keyword
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        TagContainsKeywordsPredicate otherTagContainsKeywordsPredicate = (TagContainsKeywordsPredicate) other;
        return tags.equals(otherTagContainsKeywordsPredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tags", tags).toString();
    }
}
