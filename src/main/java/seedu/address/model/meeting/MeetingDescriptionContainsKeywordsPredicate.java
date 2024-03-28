
package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Meeting}'s {@code Description} matches any of the keywords given.
 */
public class MeetingDescriptionContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public MeetingDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(meeting.getDescription(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingDescriptionContainsKeywordsPredicate)) {
            return false;
        }

        MeetingDescriptionContainsKeywordsPredicate otherMeetingDescriptionContainsKeywordsPredicate =
                (MeetingDescriptionContainsKeywordsPredicate) other;
        return keywords.equals(otherMeetingDescriptionContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
