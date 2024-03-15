package seedu.findvisor.model.person;

import java.util.function.Predicate;

import seedu.findvisor.commons.util.StringUtil;
import seedu.findvisor.commons.util.ToStringBuilder;

/**
 * A predicate for evaluating if a {@link Person}'s name contains (case-insensitive) a given keyword.
 * This is used to filter for persons based on their name attribute.
 */
public class NameContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Constructs an {@code NameContainsKeywordPredicate} with the specified keyword.
     *
     * @param keyword The keyword to be used to lookup against the person's name. The match is case-insensitive.
     */
    public NameContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getName().fullName, keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordPredicate)) {
            return false;
        }

        NameContainsKeywordPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordPredicate) other;
        return keyword.equals(otherNameContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", keyword).toString();
    }
}
