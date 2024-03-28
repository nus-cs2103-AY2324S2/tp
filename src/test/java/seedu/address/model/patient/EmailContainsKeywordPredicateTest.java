package seedu.address.model.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Email} matches a part of the keywords given.
 */
public class EmailContainsKeywordPredicateTest implements Predicate<Patient> {
    private final List<String> keywords;

    public EmailContainsKeywordPredicateTest(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsStringIgnoreCase(patient.getEmail().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmailContainsKeywordPredicateTest)) {
            return false;
        }

        EmailContainsKeywordPredicateTest otherNameContainsKeywordsPredicate =
                (EmailContainsKeywordPredicateTest) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
