package seedu.address.model.person;

import seedu.address.logic.commands.FindCommand;

import java.util.function.Predicate;

/**
 * Tests if a {@code Person}'s {@code Phone} matches with the number given
 */
public class PhoneMatchesPredicate implements Predicate<Person> {
    private final String numToMatch;

    public PhoneMatchesPredicate(String s) {
        numToMatch = s;
    }

    /**
     * Returns true if a person's {@code Phone} starts with or matches the given number.
     * False otherwise
     *
     * @param person the person to check against
     * @return if a person's phone starts with or fully matches the given number
     */
    @Override
    public boolean test(Person person) {
        if (numToMatch.equals(FindCommand.NOT_REQUIRED_VALUE)) {
            return true;
        } else {
            return person.getPhone().value.startsWith(numToMatch.trim());
        }
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
