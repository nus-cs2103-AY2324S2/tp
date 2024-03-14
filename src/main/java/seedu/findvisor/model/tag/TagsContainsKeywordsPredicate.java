package seedu.findvisor.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.findvisor.commons.util.ToStringBuilder;
import seedu.findvisor.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code tags} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword -> person.getTags().stream()
                .anyMatch(tag -> keyword.equalsIgnoreCase(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagsContainsKeywordsPredicate)) {
            return false;
        }

        TagsContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (TagsContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tags", keywords).toString();
    }
}