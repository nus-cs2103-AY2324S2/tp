package educonnect.model.student;

import java.util.function.Predicate;

import educonnect.commons.util.StringUtil;
import educonnect.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Student> {
    private final String keywordName;

    public NameContainsKeywordsPredicate(String keywordName) {
        this.keywordName = keywordName; //replace
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.fuzzyMatchIgnoreCase(student.getName().fullName, keywordName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keywordName.equals(otherNameContainsKeywordsPredicate.keywordName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywordName).toString();
    }
}
