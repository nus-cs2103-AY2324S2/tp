package educonnect.model.student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import educonnect.commons.util.StringUtil;
import educonnect.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;
    private final String keywordName;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.keywordName = ""; //replace
    }

    public NameContainsKeywordsPredicate(String keywordName) {
        this.keywords = new ArrayList<String>();
        this.keywordName = keywordName; //replace
    }

    // @Override
    // public boolean test(Student student) {
    //     return keywords.stream()
    //             .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getName().fullName, keyword));
    // }

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
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
