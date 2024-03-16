package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} or {@code id} matches the query.
 */
public class IdOrNameContainsQueryPredicate implements Predicate<Person> {

    private final String query;

    public IdOrNameContainsQueryPredicate(String query) {
        this.query = query;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getName().fullName, query) ||
                StringUtil.containsIgnoreCase(person.getId().id, query);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdOrNameContainsQueryPredicate)) {
            return false;
        }

        IdOrNameContainsQueryPredicate otherIdOrNameContainsQueryPredicate = (IdOrNameContainsQueryPredicate) other;
        return query.equals(otherIdOrNameContainsQueryPredicate.query);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", query).toString();
    }
}
