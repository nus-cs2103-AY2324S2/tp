package seedu.address.model.person;

import seedu.address.logic.commands.FindCommand;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Email} matches the keyword given
 */
public class EmailMatchesPredicate implements Predicate<Person> {
    private final String keyword;

    public EmailMatchesPredicate(String s) {
        keyword = s;
    }

    @Override
    public boolean test(Person person) {
        if (keyword.equals(FindCommand.NOT_REQUIRED_VALUE)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmailMatchesPredicate)) {
            return false;
        }

        EmailMatchesPredicate e = (EmailMatchesPredicate) other;
        return keyword.equals(e.keyword);
    }

}
