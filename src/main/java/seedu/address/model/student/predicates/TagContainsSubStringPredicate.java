package seedu.address.model.student.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.student.Student;

/**
 * Tests that any of a {@code Student}'s {@code Tags} contains the sub string specified.
 */
public class TagContainsSubStringPredicate implements Predicate<Student> {
    private final String subString;

    public TagContainsSubStringPredicate(String subString) {
        this.subString = subString;
    }

    @Override
    public boolean test(Student student) {
        return student.getTags()
                .stream()
                .anyMatch(tag -> StringUtil.containsSubstringIgnoreCase(tag.tagName, subString));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagContainsSubStringPredicate)) {
            return false;
        }

        TagContainsSubStringPredicate otherTagContainsKeywordsPredicate = (TagContainsSubStringPredicate) other;
        return subString.equals(otherTagContainsKeywordsPredicate.subString);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sub string", subString).toString();
    }
}
