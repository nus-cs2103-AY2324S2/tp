package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code StudentId} matches the keyword given.
 */
public class StudentIdContainsKeywordPredicate implements Predicate<Person> {

    private final String keyword;

    public StudentIdContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsPartialWordIgnoreCase(person.getStudentId().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentIdContainsKeywordPredicate)) {
            return false;
        }

        StudentIdContainsKeywordPredicate otherStudentIdContainsKeywordPredicate =
                (StudentIdContainsKeywordPredicate) other;
        return keyword.equals(otherStudentIdContainsKeywordPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
