package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.FindCommand;

/**
 * Tests that a {@code Person}'s {@code Email} matches the keyword given
 */
public class EmailMatchesPredicate implements Predicate<Person> {
    private final String keyword;

    public EmailMatchesPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        if (keyword.equals(FindCommand.NOT_REQUIRED_VALUE)) {
            return true;
        } else {
            return person.getEmail().value.toLowerCase().contains(keyword.trim().toLowerCase());
        }
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("emailKeyword", keyword)
                .toString();
    }
}
