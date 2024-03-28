package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that any of {@code student}'s {@code Timeslots} matches any of the keywords given.
 */
public class TimeslotsContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public TimeslotsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return student.getTimeslots().stream()
                .anyMatch(timeslot -> keywords.stream()
                        .anyMatch(keyword -> timeslot.timeslot.toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimeslotsContainsKeywordsPredicate)) {
            return false;
        }

        TimeslotsContainsKeywordsPredicate otherTimeslotsContainsKeywordsPredicate =
                (TimeslotsContainsKeywordsPredicate) other;
        return keywords.equals(otherTimeslotsContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
