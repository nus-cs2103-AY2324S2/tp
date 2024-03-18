package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code TechStack} matches any of the keywords given.
 */
public class TechStackContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> techKeywords;

    public TechStackContainsKeywordsPredicate(List<String> techKeywords) {
        this.techKeywords = techKeywords;
    }

    @Override
    public boolean test(Person person) {
        return techKeywords.stream()
                .allMatch(keyword ->
                        person.getTechStack().stream()
                                .anyMatch(ts ->
                                        StringUtil.containsWordIgnoreCase(ts.techStackName, keyword)
                                )
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagsContainKeywordsPredicate)) {
            return false;
        }

        TechStackContainsKeywordsPredicate otherContainsTechStackKeywordsPredicate = (TechStackContainsKeywordsPredicate) other;
        return techKeywords.equals(otherContainsTechStackKeywordsPredicate.techKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("techKeywords", techKeywords).toString();
    }
}