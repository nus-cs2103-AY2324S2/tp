package seedu.findvisor.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.findvisor.commons.util.StringUtil;
import seedu.findvisor.commons.util.ToStringBuilder;
import seedu.findvisor.model.person.Person;

/**
 * A predicate for evaluating if a {@link Person}'s name contains (case-insensitive) any of the given keywords.
 * This is used to filter for persons based on their tags attribute.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs an {@code TagsContainsKeywordsPredicate} with the specified keyword.
     *
     * @param keywords The keywords to be used to lookup against the person's tags.
     */
    public TagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword -> person.getTags().stream()
                .anyMatch(tag -> StringUtil.containsIgnoreCase(tag.tagName, keyword)));
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
