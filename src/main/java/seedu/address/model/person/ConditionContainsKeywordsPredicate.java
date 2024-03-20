package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Condition} matches any of the keywords given.
 */
public class ConditionContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ConditionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getCondition().toString(), keyword.trim()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConditionContainsKeywordsPredicate)) {
            return false;
        }

        ConditionContainsKeywordsPredicate otherConditionContainsKeywordsPredicate = (ConditionContainsKeywordsPredicate) other;
        return keywords.equals(otherConditionContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}