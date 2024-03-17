package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code id} matches the query ID.
 */
public class IdAndNameContainsQueryIdAndNamePredicate implements Predicate<Person> {

    private final String queryId;

    private final String queryName;

    /**
     * Constructor for the predicate.
     *
     * @param queryId Id to be queried.
     * @param queryName Name to be queried.
     */
    public IdAndNameContainsQueryIdAndNamePredicate(String queryId, String queryName) {
        this.queryId = queryId;
        this.queryName = queryName;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getId().id, queryId)
                && StringUtil.containsIgnoreCase(person.getName().fullName, queryName);
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
        return queryName.equals(otherIdAndNameContainsQueryIdAndNamePredicate.queryName);
    }

    @Override
    public String toString() {
        List<String> predicates = new ArrayList<>();
        predicates.add(queryId);
        predicates.add(queryName);
        return new ToStringBuilder(this).add("Query ID & Name: ", predicates).toString();
    }
}
