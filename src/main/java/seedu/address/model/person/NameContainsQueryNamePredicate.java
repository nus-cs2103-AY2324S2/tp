package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code id} matches the query ID.
 */
public class NameContainsQueryNamePredicate implements Predicate<Person> {

    private final String queryName;

    public NameContainsQueryNamePredicate(String queryName) {
        this.queryName = queryName;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getName().fullName, queryName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsQueryNamePredicate)) {
            return false;
        }

        NameContainsQueryNamePredicate otherNameContainsQueryNamePredicate = (NameContainsQueryNamePredicate) other;
        return queryName.equals(otherNameContainsQueryNamePredicate.queryName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("Query Name: ", queryName).toString();
    }
}
