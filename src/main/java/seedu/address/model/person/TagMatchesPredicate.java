package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.person.Person;


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
        return false;
    }
}
