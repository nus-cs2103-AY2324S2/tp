package seedu.findvisor.model.person;

import java.util.function.Predicate;

import seedu.findvisor.commons.util.StringUtil;
import seedu.findvisor.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} contains the keyword given.
 */
public class NameContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public NameContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.startsWithSubStringIgnoreCase(person.getName().fullName, keyword);
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
