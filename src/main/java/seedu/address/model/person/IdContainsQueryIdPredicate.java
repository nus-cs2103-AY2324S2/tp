package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches the query Name.
 */
public class IdContainsQueryIdPredicate implements Predicate<Person> {

    private final String queryId;

    public IdContainsQueryIdPredicate(String queryId) {
        this.queryId = queryId;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getId().id, queryId);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdContainsQueryIdPredicate)) {
            return false;
        }

        IdContainsQueryIdPredicate otherIdContainsQueryIdPredicate = (IdContainsQueryIdPredicate) other;
        return queryId.equals(otherIdContainsQueryIdPredicate.queryId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("Query ID:", queryId).toString();
    }
}
