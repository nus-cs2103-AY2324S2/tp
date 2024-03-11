package seedu.address.model.person;

import java.util.function.Predicate;

public class GroupMatchesPredicate implements Predicate<Person> {
    private final String keyword;

    public GroupMatchesPredicate(String s) {
        keyword = s;
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
        return keyword.equals(e.keyword);
    }
}
