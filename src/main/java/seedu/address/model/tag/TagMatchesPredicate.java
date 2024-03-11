package seedu.address.model.tag;

import java.util.function.Predicate;

import seedu.address.model.person.Person;


/**
 * Tests if a {@code Person}'s {@code Tag} matches the Enum given
 */
public class TagMatchesPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return false;
    }
}
