package seedu.address.model.tag;

import seedu.address.model.person.Person;

import java.util.function.Predicate;

/**
 * Tests if a {@code Person}'s {@code Tag} matches the Enum given
 */
public class TagMatchesPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return false;
    }
}
