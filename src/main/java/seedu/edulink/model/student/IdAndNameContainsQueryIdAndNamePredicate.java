package seedu.edulink.model.student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.edulink.commons.util.StringUtil;
import seedu.edulink.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code id} matches the query ID.
 */
public class IdAndNameContainsQueryIdAndNamePredicate implements Predicate<Student> {

    private final String queryId;

    private final String queryName;

    /**
     * Constructor for the predicate.
     *
     * @param queryId   Id to be queried.
     * @param queryName Name to be queried.
     */
    public IdAndNameContainsQueryIdAndNamePredicate(String queryId, String queryName) {
        this.queryId = queryId;
        this.queryName = queryName;
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.containsIngnoreCase(student.getId().id, queryId)
            && StringUtil.containsOrderedSubstringIgnoreCase(student.getName().fullName, queryName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdAndNameContainsQueryIdAndNamePredicate)) {
            return false;
        }

        IdAndNameContainsQueryIdAndNamePredicate otherIdAndNameContainsQueryIdAndNamePredicate =
            (IdAndNameContainsQueryIdAndNamePredicate) other;
        return queryName.equals(otherIdAndNameContainsQueryIdAndNamePredicate.queryName)
            && queryId.equals(otherIdAndNameContainsQueryIdAndNamePredicate.queryId);
    }

    @Override
    public String toString() {
        List<String> predicates = new ArrayList<>();
        predicates.add(queryId);
        predicates.add(queryName);
        return new ToStringBuilder(this).add("Query ID & Name: ", predicates).toString();
    }
}
