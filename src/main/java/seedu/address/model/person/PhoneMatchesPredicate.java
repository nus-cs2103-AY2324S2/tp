package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests if a {@code Person}'s {@code Phone} matches with the number given
 */
public class PhoneMatchesPredicate implements Predicate<Person> {
    private final String numToMatch;

    public PhoneMatchesPredicate(String s) {
        numToMatch = s;
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
        if (!(other instanceof PhoneMatchesPredicate)) {
            return false;
        }

        PhoneMatchesPredicate predicate = (PhoneMatchesPredicate) other;
        return numToMatch.equals(predicate.numToMatch);
    }
}
