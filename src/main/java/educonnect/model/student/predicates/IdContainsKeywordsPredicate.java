package educonnect.model.student.predicates;

import java.util.function.Predicate;

import educonnect.commons.util.StringUtil;
import educonnect.commons.util.ToStringBuilder;
import educonnect.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Id} matches any of the keywords given.
 */
public class IdContainsKeywordsPredicate implements Predicate<Student> {
    private final String keywordId;

    public IdContainsKeywordsPredicate(String keywordId) {
        this.keywordId = keywordId; //replace
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.fuzzyMatchIgnoreCase(student.getStudentId().value, keywordId);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdContainsKeywordsPredicate)) {
            return false;
        }

        IdContainsKeywordsPredicate otherIdContainsKeywordsPredicate = (IdContainsKeywordsPredicate) other;
        return keywordId.equals(otherIdContainsKeywordsPredicate.keywordId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywordId).toString();
    }
}
