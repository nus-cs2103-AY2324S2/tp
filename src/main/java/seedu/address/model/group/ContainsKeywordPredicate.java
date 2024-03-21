package seedu.address.model.group
        ;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.group.Group;

/**
 * Tests that a {@code CourseMate}'s {@code Name} matches any of the keywords given.
 */
public class ContainsKeywordPredicate implements Predicate<Group> {
    private final String keyword;

    public ContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Group group) {
        // TODO: Handle matches of groups.
        return StringUtil.containsIgnoreCase(group.getName().fullName, this.keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContainsKeywordPredicate)) {
            return false;
        }

        ContainsKeywordPredicate otherContainsKeywordsPredicate = (ContainsKeywordPredicate) other;
        return keyword.contentEquals(otherContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
