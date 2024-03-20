package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;


/**
 * Tests that a {@code Person}'s {@code tags} matches any of the keywords given.
 */
public class TagContainsKeywordPredicate implements Predicate<Person> {
    private final List<Tag> tags;

    /**
     * Returns a TagContainsKeywordsPredicate object by taking a list of the tag names.
     */
    public TagContainsKeywordPredicate(List<String> keywords) {

        this.tags = keywords.stream().map(Tag::new).collect(Collectors.toList());
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> personTags = person.getTags();
        return tags.stream()
                .anyMatch(personTags::contains);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagContainsKeywordPredicate)) {
            return false;
        }

        TagContainsKeywordPredicate otherNameContainsKeywordsPredicate = (TagContainsKeywordPredicate) other;
        return tags.equals(otherNameContainsKeywordsPredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tags", tags).toString();
    }
}
