package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code LastContact} is present.
 */
public class HasLastContactedPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return person.getLastcontact().hasLastContacted();
    }
}
