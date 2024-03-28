package seedu.findvisor.model.tag;

import java.util.List;
import java.util.stream.Collectors;

import seedu.findvisor.commons.util.StringUtil;
import seedu.findvisor.commons.util.ToStringBuilder;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.person.PersonPredicate;

/**
 * A predicate for evaluating if a {@link Person}'s name contains (case-insensitive) any of the given keywords.
 * This is used to filter for persons based on their tags attribute.
 */
public class PersonTagsPredicate implements PersonPredicate {
    private final List<String> keywords;

    /**
     * Constructs an {@code TagsContainsKeywordsPredicate} with the specified keyword.
     *
     * @param keywords The keywords to be used to lookup against the person's tags.
     */
    public PersonTagsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns the description of this predicate, indicating the tags keywords criteria.
     *
     * @return A string describing the predicate
     */
    public String getPredicateDescription() {
        String tagsString = keywords.stream()
                .map(tag -> "\"" + tag + "\"")
                .collect(Collectors.joining(", "));

        return String.format("Tags = %1$s", tagsString);
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
        if (!(other instanceof PersonTagsPredicate)) {
            return false;
        }

        PersonTagsPredicate otherNameContainsKeywordsPredicate = (PersonTagsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tags", keywords).toString();
    }
}
