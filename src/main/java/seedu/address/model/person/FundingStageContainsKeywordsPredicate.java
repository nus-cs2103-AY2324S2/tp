package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code FundingStage} matches any of the keywords given.
 */
public class FundingStageContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FundingStageContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getFundingStage().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FundingStageContainsKeywordsPredicate)) {
            return false;
        }

        FundingStageContainsKeywordsPredicate otherFundingStageContainsKeywordsPredicate = (FundingStageContainsKeywordsPredicate) other;
        return keywords.equals(otherFundingStageContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
