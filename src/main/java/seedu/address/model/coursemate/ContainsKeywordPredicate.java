package seedu.address.model.coursemate;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code CourseMate}'s {@code Name} matches any of the keywords given.
 */
public class ContainsKeywordPredicate implements Predicate<CourseMate> {
    private final String keyword;

    public ContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(CourseMate courseMate) {
        // TODO: Handle matches of groups.
        return StringUtil.containsIgnoreCase(courseMate.getName().fullName, this.keyword)
            || courseMate.getSkills().stream().anyMatch(skill -> skill.skillName.contentEquals(this.keyword));
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
