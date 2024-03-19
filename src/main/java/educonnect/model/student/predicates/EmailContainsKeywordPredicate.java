package educonnect.model.student.predicates;

import java.util.function.Predicate;

import educonnect.commons.util.StringUtil;
import educonnect.commons.util.ToStringBuilder;
import educonnect.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordPredicate implements Predicate<Student> {
    private final String keywordEmail;

    public EmailContainsKeywordPredicate(String keywordEmail) {
        this.keywordEmail = keywordEmail; //replace
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.fuzzyMatchIgnoreCase(student.getEmail().value, keywordEmail);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmailContainsKeywordPredicate)) {
            return false;
        }

        EmailContainsKeywordPredicate otherEmailContainsKeywordsPredicate = (EmailContainsKeywordPredicate) other;
        return keywordEmail.equals(otherEmailContainsKeywordsPredicate.keywordEmail);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywordEmail).toString();
    }
}
