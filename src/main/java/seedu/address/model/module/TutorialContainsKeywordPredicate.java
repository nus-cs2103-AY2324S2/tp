package seedu.address.model.module;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code TutorialClass} matches the keyword given.
 */
public class TutorialContainsKeywordPredicate implements Predicate<Person> {

    private final String keyword;

    public TutorialContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsPartialWordIgnoreCase(person.getTutorialClass().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorialContainsKeywordPredicate)) {
            return false;
        }

        TutorialContainsKeywordPredicate otherTutorialContainsKeywordPredicate =
                (TutorialContainsKeywordPredicate) other;
        return keyword.equals(otherTutorialContainsKeywordPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
