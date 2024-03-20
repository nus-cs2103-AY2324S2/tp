package seedu.address.model.contact;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code TechStack} matches any of the keywords given.
 */
public class TsContainsKeywordsPredicate implements Predicate<Contact> {
    private final List<String> techKeywords;

    public TsContainsKeywordsPredicate(List<String> techKeywords) {
        this.techKeywords = techKeywords;
    }

    @Override
    public boolean test(Contact contact) {
        return techKeywords.stream().allMatch(keyword -> 
            contact.getTechStack().stream().anyMatch(ts ->
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

        TsContainsKeywordsPredicate otherContainsTechStackKeywordsPredicate = (TsContainsKeywordsPredicate) other;
        return techKeywords.equals(otherContainsTechStackKeywordsPredicate.techKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("techKeywords", techKeywords).toString();
    }
}
