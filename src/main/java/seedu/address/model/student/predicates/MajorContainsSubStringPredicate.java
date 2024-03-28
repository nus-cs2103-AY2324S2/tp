package seedu.address.model.student.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.student.Student;

/**
 * Tests that a {@code student}'s {@code Major} contains the sub string specified.
 */
public class MajorContainsSubStringPredicate implements Predicate<Student> {
    private final String subString;

    public MajorContainsSubStringPredicate(String subString) {
        this.subString = subString;
    }

    @Override
    public boolean test(Student student) {
        if (subString.isEmpty() || subString.isBlank()) {
            return false;
        }
        return StringUtil.containsSubstringIgnoreCase(student.getMajor().toString(), subString);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MajorContainsSubStringPredicate)) {
            return false;
        }

        MajorContainsSubStringPredicate otherMajorContainsKeywordsPredicate = (MajorContainsSubStringPredicate) other;
        return subString.equals(otherMajorContainsKeywordsPredicate.subString);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sub string", subString).toString();
    }
}
