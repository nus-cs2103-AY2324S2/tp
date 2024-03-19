package educonnect.model.student.predicates;

import educonnect.commons.util.ToStringBuilder;
import educonnect.model.student.Tag;
import educonnect.model.student.Student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Tag} matches the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Student> {

    private final Tag keywordTag;

    public TagContainsKeywordsPredicate(String keywordTag) {
        this.keywordTag = new Tag(keywordTag); //replace
    }

    @Override
    public boolean test(Student student) {
        return student.getTags().contains(keywordTag);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagContainsKeywordsPredicate)) {
            return false;
        }

        TagContainsKeywordsPredicate otherTagContainsKeywordsPredicate = (TagContainsKeywordsPredicate) other;
        return keywordTag.equals(otherTagContainsKeywordsPredicate.keywordTag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywordTag).toString();
    }
}
