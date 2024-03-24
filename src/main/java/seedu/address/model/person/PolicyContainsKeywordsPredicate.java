package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Policy} matches any of the keywords given.
 */
public class PolicyContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PolicyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyContainsKeywordsPredicate)) {
            return false;
        }

        PolicyContainsKeywordsPredicate otherPolicyContainsKeywordsPredicate =
                (PolicyContainsKeywordsPredicate) other;
        return keywords.equals(otherPolicyContainsKeywordsPredicate.keywords);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPolicy().value, keyword));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
