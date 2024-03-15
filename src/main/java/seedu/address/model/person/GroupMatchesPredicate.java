package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests if a keyword matches any {@code Person}'s {@code Groups}
 */
public class GroupMatchesPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public GroupMatchesPredicate(List<String> s) {
        keywords = s;
    }

    @Override
    public boolean test(Person person) {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupMatchesPredicate)) {
            return false;
        }

        GroupMatchesPredicate e = (GroupMatchesPredicate) other;
        return keywords.equals(e.keywords);
    }
}
