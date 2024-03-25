package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * A predicate that checks if a {@code Person} has {@code Upcoming} appointments.
 */
public class HasUpcomingPredicate implements Predicate<Person> {

    /**
     * Tests if the given person has upcoming events.
     *
     * @param person The person to be tested.
     * @return true if the person has upcoming events, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        return person.getUpcoming().hasUpcoming();
    }
}
