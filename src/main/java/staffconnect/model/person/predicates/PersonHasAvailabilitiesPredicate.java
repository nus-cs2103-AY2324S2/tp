package staffconnect.model.person.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.model.availability.Availability;
import staffconnect.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Availabilities} matches any of the
 * availabilities given.
 */
public class PersonHasAvailabilitiesPredicate implements Predicate<Person> {
    private final Set<Availability> availabilities;

    public PersonHasAvailabilitiesPredicate(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    @Override
    public boolean test(Person person) {
        if (availabilities == null) {
            return true;
        }
        // get list of person availabilities
        List<String> personAvailabilities = person.getAvailabilities().stream().map(a -> a.value.toLowerCase())
                .collect(Collectors.toList());
        // get stream of availabilities to filter from
        Stream<String> availabilitiesToFilter = availabilities.stream().map(a -> a.value.toLowerCase())
                .collect(Collectors.toList()).stream();
        // check if the person DOES NOT contain any of the availabilities to filter from, if true
        // then predicate is not satisfied
        return !availabilitiesToFilter.anyMatch(availability -> !personAvailabilities.contains(availability));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonHasAvailabilitiesPredicate)) {
            return false;
        }

        PersonHasAvailabilitiesPredicate otherPersonHasAvailabilitiesPredicate =
                (PersonHasAvailabilitiesPredicate) other;
        return availabilities.equals(otherPersonHasAvailabilitiesPredicate.availabilities);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("availabilities", availabilities).toString();
    }
}
