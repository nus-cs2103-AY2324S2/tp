package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;


/**
 * Tests if a {@code Person}'s {@code Tag} matches the Enum given
 */
public class TagMatchesPredicate implements Predicate<Person> {
    private final String keyword;

    public TagMatchesPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        if (keyword.equals(FindCommand.NOT_REQUIRED_VALUE)) {
            return true;
        } else if (!Tag.isValidTag(keyword)) {
            return false;
        } else {
            return person.getTag().equals(new Tag(keyword));
        }
    }
}
